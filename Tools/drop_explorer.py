import sys
import json
import argparse
import os
import re
from collections import defaultdict

source_files = "../Server/data/configs/{npc_configs,item_configs,drop_tables}.json"

def json_root_name(path):
    '''Given a path to a json file path, 
    return the string before '.json' and after the last "/"
    '''
    return os.path.basename(os.path.splitext(path)[0])

assert json_root_name("../Server/data/configs/item_configs.json") == "item_configs", "Failed to parse out root object name from JSON path"

"""
Compute the Damerau-Levenshtein distance between two given
strings (s1 and s2)

From here: https://www.guyrutenberg.com/2008/12/15/damerau-levenshtein-distance-in-python/
"""
def damerau_levenshtein_distance(s1, s2):
    d = {}
    lenstr1 = len(s1)
    lenstr2 = len(s2)
    for i in range(-1,lenstr1+1):
        d[(i,-1)] = i+1
    for j in range(-1,lenstr2+1):
        d[(-1,j)] = j+1

    for i in range(lenstr1):
        for j in range(lenstr2):
            if s1[i] == s2[j]:
                cost = 0
            else:
                cost = 1
            d[(i,j)] = min(
                           d[(i-1,j)] + 1, # deletion
                           d[(i,j-1)] + 1, # insertion
                           d[(i-1,j-1)] + cost, # substitution
                          )
            if i and j and s1[i]==s2[j-1] and s1[i-1] == s2[j]:
                d[(i,j)] = min (d[(i,j)], d[i-2,j-2] + cost) # transposition

    return d[lenstr1-1,lenstr2-1]

class Item:
    attributes = {"id"
        , "name"
        , "examine"
        , "destroy"
        , "tradeable"
        , "destroy_message"
        , "shop_price"
        , "weight"
        , "archery_ticket_price"
        , "durability"
        , "low_alchemy"
        , "high_alchemy"
        , "grand_exchange_price"
        , "ge_buy_limit"
        , "requirements"
        , "render_anim"
        , "equipment_slot"
        , "attack_speed"
        , "bonuses"
        , "weapon_interface"
        , "has_special"
        , "attack_anims"
        , "defence_anim"
        , "attack_audios"
        , "remove_head"
        , "remove_sleeves"
        , "tokkul_price"
        , "bankable"
        , "stand_anim"
        , "walk_anim"
        , "run_anim"
        , "stand_turn_anim"
        , "turn180_anim"
        , "turn90cw_anim"
        , "turn90ccw_anim"
        , "two_handed"
        , "absorb"
        , "point_price"
        , "lendable"
        , "fun_weapon"
        , "rare_item"
        , "remove_beard"
    }
    def __init__(self, item_dict, reporter=None):
        for attribute in self.attributes:
            self.__dict__[attribute] = None
        for attribute, value in item_dict.items():
            if attribute not in self.attributes:
                if reporter:
                    reporter.report(attribute)
                else:
                    raise AttributeError(f"Unhandled item attribute '{attribute}'")
            self.__dict__[attribute] = value


class Drop:
    attributes = {
       "minAmount"
       , "weight"
       , "id"
       , "maxAmount" 
    }
    def __init__(self, drop_dict, storage=None, reporter=None):
        for attribute in self.attributes:
            self.__dict__[attribute] = None
        for attribute, value in drop_dict.items():
            if attribute not in self.attributes:
                if reporter:
                    reporter.report(attribute)
                else:
                    raise AttributeError(f"Unhandled item attribute '{attribute}'")
            self.__dict__[attribute] = value
        
        #'''
        if self.id not in storage.item_id_to_item:
            print("Item in drop table is NOT in the item configs:", item_dict)
        #'''
        self.item = storage.item_id_to_item.get(self.id, None)

class DropTable:
    attributes = {
        "ids"
        , "main"
        , "charm"
        , "default"
        , "description"
    }
    def __init__(self, table_dict, storage=None, reporter=None):
        for attribute in self.attributes:
            self.__dict__[attribute] = None
        for attribute, value in table_dict.items():
            if attribute not in self.attributes:
                if reporter:
                    reporter.report(attribute)
                else:
                    raise AttributeError(f"Unhandled item attribute '{attribute}'")
            self.__dict__[attribute] = value

        self.npcs = self.ids.split(',')
        self.drops = [Drop(drop, storage, reporter) 
            for drop in self.main
        ]

class Reporter:
    def __init__(self):
        self.cache = set()
    def report(self, item):
        self.cache.add(item)
    def __repr__(self):
        return str(self.cache)

class LookupStorage:
    '''Simple object to parse and store the parsed JSON dictionaries as attributes'''
    lookups = ["npc_configs", "item_configs", "drop_tables"]
    root_path = "../Server/data/configs/"
    def __init__(self, root_path=None):
        self.attributes = {}

        if root_path is None:
            root_path = self.root_path
        
        for lookup in self.lookups:
            filename = f"{os.path.join(root_path, lookup + '.json')}"
            try:
                with open(filename, 'r') as f:
                    self.attributes[lookup] = json.load(f)
            except OSError:
                print(f"Unable to find file {filename}, are you sure you have the right path?"
                    , file=sys.stderr
                )
                raise
            except json.JSONDecodeError:
                print(f"Error parsing {filename}, is it a valid JSON object?"
                    , file=sys.stderr
                )

        # setup fuzzy search table
        self.npc_names = [npc['name'] 
            for npc in self.npc_configs
                if 'name' in npc
        ]

        self.item_names = [item['name']
            for item in self.item_configs
                if 'name' in item
        ]

        # Assuming every item has an id, so we dont have to check for it
        # This MUST be initialized before the drop tables!
        self.item_id_to_item = {item['id'] : Item(item)
            for item in self.item_configs
        }

        self.droptables = [DropTable(table, self)
            for table in self.drop_tables
        ]

        self.npc_id_to_droptable = {_id : table
            for table in self.droptables for _id in table.npcs
        }

        self.item_name_to_items = defaultdict(list)
        for item in self.item_configs:
            if 'name' in item:
                self.item_name_to_items[item['name']].append(Item(item))

        # Maps from name->ID for cross referencing
        self.npc_name_to_id = {npc['name'] : npc['id']
            for npc in self.npc_configs
                if 'name' in npc
        }

        self.npc_id_to_name = {npc['id'] : npc['name']
            for npc in self.npc_configs
                if 'name' in npc
        }

    def __getattr__(self, attr):
        if (res := self.__dict__.get(attr, None)) is not None:
            return res
        if (res := self.attributes.get(attr, None)) is not None:
            return res
        raise AttributeError

    def fuzzyname_to_name(self, fuzzy, candidates):
        '''Implement an approximate string matching strategy to guess which name the user means'''
        # making everything lower case makes it easier to work with!
        fuzzy = fuzzy.lower()

        # Replace common abbreviations with something closer to what we'll find
        replacements = [ 
            ('abby', 'abyssal'), 
            ('addy', 'adamant'),
            ('mith', 'mithril'),
            (' legs', ' platelegs'),
            (' skirt', ' plateskirt'),
            (' kite', ' kiteshield'),
            
        ]
        orig = fuzzy[:] # make a copy of the original string and try both
        for common, real in replacements:
            fuzzy = fuzzy.replace(common, real)

        min_string = None
        min_distance = 10000
        for query in [fuzzy]:
            for name in candidates:
                if (distance := damerau_levenshtein_distance(name.lower(), query)) < min_distance:
                    min_distance = distance
                    min_string = name

        return min_string

    def fuzzyname_to_npc_id(self, fuzzy):
        return self.npc_name_to_id.get(
            self.fuzzyname_to_name(fuzzy, self.npc_names)
            , None
        )

    def droptable_from_npc_name(self, name, name_is_fuzzy=True):
        # we have this kwarg because if you don't, even for NPCs with a name
        # that's easy to look up, it will calculate the DL edit distance
        # and you easily end up with an O(n^3)

        # you could also look in the map first and call the fuzzyname if you dont find it
        if name_is_fuzzy:
            npcid = self.fuzzyname_to_npc_id(name)
        else:
            npcid = self.npc_name_to_id.get(name, None)
        table = self.npc_id_to_droptable.get(npcid, None)
        return table

    def calc_alch_value(storage, name
        , include_rare=False
        , min_alch_value=1500
        , name_is_fuzzy=False
        , verbose=False):
        '''Find alch value of drop table of a given monster
        
        Sum up everything in name's drop table that alchs for more than min_alch_value
        Normalize by weight. If the drop can be multiple, only take 1 item into account,
        because you need 1 nat per alch anyways.

        That may be wrong but for big ticket items (eg rune) it wont matter, and
        that's the goal anyways!
        '''

        total_alch = 0
        total_weight = 0
        table = storage.droptable_from_npc_name(name, name_is_fuzzy)
        if table is None:
            return -1
        for drop in table.drops:
            weight = int(drop.weight)
            total_weight = total_weight + weight
            if (gp := drop.item.high_alchemy):
                gp = int(gp)
                if not include_rare and bool(drop.item.rare_item):
                    continue
                if gp < min_alch_value:
                    continue
                if verbose:
                    print(f"Found alchable in {name}'s table: {drop.item.name} for {gp}gp")
                total_alch = total_alch + weight * gp

        # Average alchable gp per kill
        return int(total_alch / total_weight) if total_weight else 0
    
    def who_drops(self, name):
        name = self.fuzzyname_to_name(name, self.item_names)
        item_ids = set(item.id for item in self.item_name_to_items.get(name, None))
        dropping_npcs = set()
        for table in self.droptables:
            if item_ids.intersection(set(drop.item.id for drop in table.drops)):
                npc_names = set(self.npc_id_to_name.get(_id, f"ID {_id}") for _id in table.npcs)
                dropping_npcs = dropping_npcs.union(npc_names)

        for npc in dropping_npcs:
            print(npc, "drops", name)

    def top_alch_values(self, candidates=None, count=10, **kwargs):
        if candidates is None:
            candidates = self.npc_names
        
        # use a set so duplicates arent counted
        alchs = {
            (name, self.calc_alch_value(name, **kwargs)) 
            for name in candidates
        }
        alchs = list(alchs)
        alchs.sort(key=(lambda x: x[1]), reverse=True)
        return alchs[:count]

    @staticmethod
    def test():
        tester = LookupStorage()
        _ = tester.npc_configs
        _ = tester.item_configs
        _ = tester.drop_tables

        assert tester.fuzzyname_to_name("Abby demon", tester.npc_names) == "Abyssal demon"
        assert tester.fuzzyname_to_name("Mith Legs", tester.item_names) == "Mithril platelegs"

        print(tester.calc_alch_value("Dark beast", verbose=True), "alchable gp per dark beast kill!")

        tester.who_drops("abby whip")
        tester.who_drops("rune kite")

        '''
        print("Monster        Average value of drops if alched")
        for name, gp in tester.top_alch_values(count=50):
            print(f"{name.ljust(20, ' ')} {gp}gp")
        '''


LookupStorage.test()

            
   # table = DropTable(
    
