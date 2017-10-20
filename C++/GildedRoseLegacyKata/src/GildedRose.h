#ifndef GILDEDROSE_H_
#define GILDEDROSE_H_

#include <vector>
#include "Item.h"

class GildedRose {
public:
  std::vector<Item>& items;
  GildedRose(std::vector<Item> & items);

  void updateQuality();
};

#endif /* GILDEDROSE_H_ */
