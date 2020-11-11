import json
import code
from pprint import pprint
import argparse
import collections
from weakref import proxy
#code.interact(local=dict(globals(), **locals()))

class Link(object):
    __slots__ = 'prev', 'next', 'key', '__weakref__'

class OrderedSet(collections.MutableSet):
	'Set the remembers the order elements were added'
	# Big-O running times for all methods are the same as for regular sets.
	# The internal self.__map dictionary maps keys to links in a doubly linked list.
	# The circular doubly linked list starts and ends with a sentinel element.
	# The sentinel element never gets deleted (this simplifies the algorithm).
	# The prev/next links are weakref proxies (to prevent circular references).
	# Individual links are kept alive by the hard reference in self.__map.
	# Those hard references disappear when a key is deleted from an OrderedSet.
	
	def __init__(self, iterable=None):
		self.__root = root = Link()         # sentinel node for doubly linked list
		root.prev = root.next = root
		self.__map = {}                     # key --> link
		if iterable is not None:
			self |= iterable
	
	def __len__(self):
		return len(self.__map)
	
	def __contains__(self, key):
		return key in self.__map
	
	def add(self, key):
		# Store new key in a new link at the end of the linked list
		if key not in self.__map:
			self.__map[key] = link = Link()            
			root = self.__root
			last = root.prev
			link.prev, link.next, link.key = last, root, key
			last.next = root.prev = proxy(link)
	
	def discard(self, key):
		# Remove an existing item using self.__map to find the link which is
		# then removed by updating the links in the predecessor and successors.        
		if key in self.__map:        
			link = self.__map.pop(key)
			link.prev.next = link.next
			link.next.prev = link.prev
	
	def __iter__(self):
		# Traverse the linked list in order.
		root = self.__root
		curr = root.next
		while curr is not root:
			yield curr.key
			curr = curr.next
	
	def __reversed__(self):
		# Traverse the linked list in reverse order.
		root = self.__root
		curr = root.prev
		while curr is not root:
			yield curr.key
			curr = curr.prev
	
	def pop(self, last=True):
		if not self:
			raise KeyError('set is empty')
		key = next(reversed(self)) if last else next(iter(self))
		self.discard(key)
		return key
	
	def __repr__(self):
		if not self:
			return '%s()' % (self.__class__.__name__,)
		return '%s(%r)' % (self.__class__.__name__, list(self))
	
	def __eq__(self, other):
		if isinstance(other, OrderedSet):
			return len(self) == len(other) and list(self) == list(other)
		return not self.isdisjoint(other)
		
		
def datalist(f):
	with open(f) as jsonFile:
		d = json.load(jsonFile)
	if (isinstance(d, dict) and len(d.keys()) == 1):
		d = d[list(d.keys())[0]]
	return d
	
def compareby(l1, l2, field):
	idx1 = [record[field] for record in l1]
	idx2 = [record[field] for record in l2]
	
	idx1Uniques = OrderedSet(idx1) - idx2
	idx2Uniques = OrderedSet(idx2) - idx1
	
	print('')
	print('[%s] not in LEFT:' % field)
	pprint(idx2Uniques)
	print('')
	print('[%s] not in RIGHT:' % field)
	pprint(idx1Uniques)
	print('')
	
	idxCommon = (OrderedSet(idx1) | OrderedSet(idx2)) - idx1Uniques - idx2Uniques
	idxCommonList = list(idxCommon)
	
	dict1 = {record[field] : {i:record[i] for i in record if i!=field} for record in l1}
	dict2 = {record[field] : {i:record[i] for i in record if i!=field} for record in l2}
	
	for idx in idxCommonList:
		if (dict1[idx] != dict2[idx]):
			print('records do not match for [%s]=%s' % (field, idx))
			print('   LEFT:  ' + str(dict1[idx]))
			print('   RIGHT: ' + str(dict2[idx]))
			print('')
	
	#code.interact(local=dict(globals(), **locals()))
	
	
	
if __name__ == "__main__":
	parser = argparse.ArgumentParser(description='Show delta to get from LEFT file to RIGHT file')
	parser.add_argument('f1', type=str, help='LEFT file')
	parser.add_argument('f2', type=str, help='RIGHT file')
	parser.add_argument('field', type=str, help='FIELD to compare by')
	args = parser.parse_args()

	d1 = datalist(args.f1)
	d2 = datalist(args.f2)
	compareby(d1, d2, args.field)
	
	#code.interact(local=dict(globals(), **locals()))
	