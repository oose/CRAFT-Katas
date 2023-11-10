#include "../src/GildedRose.h"
#include <gtest/gtest.h>

TEST (GildedRoseTest, SomeFailingTest) {
  std::vector<Item> items;
  items.push_back(Item("Foo", 0, 0));
  GildedRose app(items);
  app.updateQuality();
  ASSERT_EQ("Fix me!", app.items[0].name);
}

int main(int argc, char **argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}