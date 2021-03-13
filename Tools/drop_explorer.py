import sys
import json
import argparse
import os
import re
from collections import defaultdict

# Command line options, with safe defaults
options = lambda:None
options.fuzzy = False

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
        self.total_weight = sum(int(drop.weight) for drop in self.drops)

class Reporter:
    def __init__(self):
        self.cache = set()
    def report(self, item):
        self.cache.add(item)
    def __repr__(self):
        return str(self.cache)

    def print_headers(self, headers):
        self.row_format = f"{{:>{3+max(len(_) for _ in headers)}}}" * len(headers)
        print(self.row_format.format(*headers))

    def print_row(self, row):
        print(self.row_format.format(*row))

    @staticmethod
    def print_table(headers, data):
        row_format = f"{{:>{3+max(len(_) for _ in headers)}}}" * len(headers)
        print(row_format.format(*headers))
        for row in data:
            print(row_format.format(*row))

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
            except json.JSONDecodeError:
                print(f"Error parsing {filename}, is it a valid JSON object?"
                    , file=sys.stderr
                )
                raise
            except FileNotFoundError:
                print(f"Couldn't find JSON files at expected location, are you running the script from the Tools/ directory? If not, try supplying --root-path as an argument"
                    , file=sys.stderr
                )
                raise
            except OSError:
                print(f"Unable to find file {filename}, are you sure you have the right --root-path?"
                    , file=sys.stderr
                )
                raise

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
            ('abby', 'abyssal', None) 
            , ('addy', 'adamant', None)
            , ('mith', 'mithril', None)
            , (' legs', ' platelegs', fuzzy.endswith)
            , (' skirt', ' plateskirt', fuzzy.endswith)
            , (' kite', ' kiteshield', fuzzy.endswith)
            , ('dh ', 'dharok', fuzzy.startswith)
            , ('kree', "kree'arra", fuzzy.__eq__)
            , (' sq', ' sq shield', fuzzy.endswith)
            , (' drag', ' dragon', fuzzy.endswith)
            , ('visage', 'draconic visage', fuzzy.__eq__)
        ]
        orig = fuzzy[:] # make a copy of the original string and try both
        for common, real, test in replacements:
            if test is not None:
                if not test(common):
                    continue
            fuzzy = fuzzy.replace(common, real)

        # User does not want us to use DL distance
        if not options.fuzzy:
            for name in candidates:
                if name.lower() == fuzzy:
                    return name
            return None

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

    def droptable_from_npc_name(self, name):

        npcid = self.npc_name_to_id.get(name, None)
        if npcid is None:
            npcid = self.fuzzyname_to_npc_id(name)
        table = self.npc_id_to_droptable.get(npcid, None)
        return table

    def calc_alch_value(storage, name
        , include_rare=False
        , min_alch_value=1500
        , verbose=False):
        '''Find alch value of drop table of a given monster
        
        Sum up everything in name's drop table that alchs for more than min_alch_value
        Normalize by weight. If the drop can be multiple, only take 1 item into account,
        because you need 1 nat per alch anyways.

        That may be wrong but for big ticket items (eg rune) it wont matter, and
        that's the goal anyways!
        '''

        total_alch = 0
        table = storage.droptable_from_npc_name(name)
        if table is None:
            return -1
        for drop in table.drops:
            weight = int(drop.weight)
            if (gp := drop.item.high_alchemy):
                gp = int(gp)
                if not include_rare and bool(drop.item.rare_item):
                    continue
                if gp < min_alch_value:
                    continue
                if verbose:
                    print(f"Found alchable in {name}'s table: {drop.item.name} for {gp}gp")
                total_alch = total_alch + weight * gp

        total_weight = table.total_weight

        # Average alchable gp per kill
        return int(total_alch / total_weight) if total_weight else 0
    
    def who_drops(self, name):
        '''Find out which NPC drops a given item name
        
        Use a set to prevent duplicate NPCs from being printed.
        If NPC doesn't have a name, print its ID, prefixed by "ID "
        '''
        
        query = self.fuzzyname_to_name(name, self.item_names)

        # One item name (eg Abyssal whip) can have multiple IDs for, eg, cosmetic
        # variants. By keeping a list of the IDs in a dictionary we can keep
        # track of all variants, and also make sure variants with higher
        # IDs don't overwrite the earlier versions of the item (which are more likely
        # to be widely used in game anyways).
        if (items := self.item_name_to_items.get(query, None)) is None:
            raise ValueError(f"Couldn't find item named {name}")

        item_ids = set(item.id for item in items)
        dropping_npcs = set()

        for table in self.droptables:
            if item_ids.intersection(set(drop.item.id for drop in table.drops)):
                npc_names = set(self.npc_id_to_name.get(_id, f"ID {_id}") for _id in table.npcs)
                dropping_npcs = dropping_npcs.union(npc_names)

        for npc in dropping_npcs:
            print(npc, "drops", query)

    def drop_table(self, name):
        '''In tabular form, print the drop table for the given NPC.
        Sort by alch value, descending (though it can be sorted in many ways)
        '''
        query = self.fuzzyname_to_name(name, self.npc_names)
        table = self.droptable_from_npc_name(query)
        if table is None:
            raise ValueError(f"No drop table identified for '{name}'")
        total_weight = table.total_weight 

        def percentage(weight_string):
            return f"{100.0*int(weight_string)/total_weight:.2f}%"
        
        def amount(drop):
            if drop.minAmount == drop.maxAmount:
                return drop.maxAmount
            return f"{drop.minAmount}-{drop.maxAmount}"

        # For items that don't have a listed alchemy value, assume 0
        rows = [(drop.item.name
                , amount(drop)
                , drop.item.high_alchemy if drop.item.high_alchemy else 0
                , drop.weight
                , percentage(drop.weight)
            )
            for drop in table.drops
        ]

        # sort by alchemy value, descending. Arbitrary choice, feel free
        # to choose otherwise
        rows.sort(key=(lambda x: int(x[2])), reverse=True)

        # Give this a nice title
        print("")
        print(30*" " + f" ==== {query} drop table ====")

        # Pad the headers a little bit so that long item names print aligned
        reporter = Reporter()
        reporter.print_headers(["Item", "Amount", " Hi Alch Value ", "Weight", "Percentage"])
        
        for row in rows:
            reporter.print_row(row)

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

    def print_alch_value(self, name, **kwargs):
        print(self.calc_alch_value(name, verbose=True, **kwargs), f"alchable gp per {name} kill!")
    
    @staticmethod
    def test():
        tester = LookupStorage()
        _ = tester.npc_configs
        _ = tester.item_configs
        _ = tester.drop_tables

        assert tester.fuzzyname_to_name("Abby demon", tester.npc_names) == "Abyssal demon"
        assert tester.fuzzyname_to_name("Mith Legs", tester.item_names) == "Mithril platelegs"

        tester.print_alch_value("Dark beast")

        tester.who_drops("abby whip")
        tester.who_drops("rune kite")

        tester.drop_table("Goblin")
        tester.drop_table("kree")

        '''
        print("Monster        Average value of drops if alched")
        for name, gp in tester.top_alch_values(count=50):
            print(f"{name.ljust(20, ' ')} {gp}gp")
        '''


def readable_dir(prospective_dir):
    '''A directory type for argparse, from here: https://stackoverflow.com/q/11415570'''
    if not os.path.isdir(prospective_dir):
        raise argparse.ArgumentTypeError("readable_dir:{0} is not a valid path".format(prospective_dir))
    if os.access(prospective_dir, os.R_OK):
        return prospective_dir
    else:
        raise argparse.ArgumentTypeError("readable_dir:{0} is not a readable dir".format(prospective_dir))


def main(*args, **kwargs):
    
    # Yes, globals are bad. We do it this way for convenience of access to CLI args.
    # Because CLI args are read-only and there's no concurrency, this is an ok practice.
    global options

    parser = argparse.ArgumentParser(
        formatter_class=argparse.ArgumentDefaultsHelpFormatter
    )
    parser.add_argument("-f", "--fuzzy", action="store_true"
        , help="Attempt to search for your query using the closest possible NPC or item name (as calculated by Damerau-Levenshtein edit distance. This may SIGNIFICANTLY slow down your query!"
    )
    parser.add_argument("-p", "--root-path"
        , help="Path to directory where all the JSON files are stored. By default assumes we're running from the Tools/ directory"
        , type=readable_dir
        , default=None
    )
    group = parser.add_mutually_exclusive_group()
    group.add_argument("-w", "--who_drops"
        , help="Print the names of all NPCs who drop a given item"
        , default=None
    )
    group.add_argument("-d", "--drop-table"
        , help="Print the drop table of the given NPC"
        , default=None
    )
    group.add_argument("-a", "--average-alch"
        , help="Print the average alch value of drops of the given NPC. Use with --min-alch-value and --include-rare"
        , default=None
    )

    parser.add_argument("-m", "--min-alch-value"
        , help="The minimum high alchemy value of an item required to be included in the alch value of an average drop"
        , default=1500
        , type=int
    )
    parser.add_argument("-r", "--include-rare"
        , help="Include rare items in alch value (but are you really gonna alch a whip, you monster?)"
        , action="store_true"
    ) 

    parser.add_argument("--test"
        , help="Run the built in tests"
        , action="store_true"
    )

    options = parser.parse_args(args)

    storage = LookupStorage(root_path=options.root_path)

    if options.who_drops:
        storage.who_drops(options.who_drops)

    if options.drop_table:
        storage.drop_table(options.drop_table)

    if options.average_alch:
        storage.print_alch_value(options.average_alch
            , min_alch_value=options.min_alch_value
            , include_rare=options.include_rare
        )
    
    if options.test:
        LookupStorage.test()

if __name__ == '__main__':
    main(*sys.argv[1:]) 
    
