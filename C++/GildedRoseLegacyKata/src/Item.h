#ifndef ITEM_H_
#define ITEM_H_

#include <string>

class Item {
public:
  std::string name;
  int sellIn;
  int quality;

  Item(std::string name, int sellIn, int quality) : name(name), sellIn(sellIn), quality(quality)
  {}
};

#endif /* ITEM_H_ */
