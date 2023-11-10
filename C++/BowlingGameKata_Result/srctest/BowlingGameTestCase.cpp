#include <gtest/gtest.h>
#include "../src/BowlingGame.h"

TEST(BowlingGameTestCase, MaximumAllowedScorePerRollIs10) {
  BowlingGame game;
  EXPECT_THROW(game.roll(11), std::invalid_argument);
}

TEST(BowlingGameTestCase, GutterGame) {
  BowlingGame game;
  for (int numberOfRolls = 0; numberOfRolls < 20; numberOfRolls++) {
    game.roll(0);
  }
  ASSERT_EQ(0, game.getScore());
}

TEST(BowlingGameTestCase, AllRollsAreOnesScores20) {
  BowlingGame game;
  for (int numberOfRolls = 0; numberOfRolls < 20; numberOfRolls++) {
    game.roll(1);
  }
  ASSERT_EQ(20, game.getScore());
}

TEST(BowlingGameTestCase, OneSpareIsScoredCorrectly) {
  BowlingGame game;
  game.roll(5);
  game.roll(5);
  game.roll(9);
  game.roll(0);
  ASSERT_EQ(28, game.getScore());
}

TEST(BowlingGameTestCase, OneStrikeIsScoredCorrectly) {
  BowlingGame game;
  game.roll(10);
  game.roll(5);
  game.roll(3);
  game.roll(0);
  ASSERT_EQ(26, game.getScore());
}

TEST(BowlingGameTestCase, ThePerfectGameIsScoredCorrectly) {
  BowlingGame game;

  for (int count = 0; count <= 11; count++) {
    game.roll(10);
  }

  ASSERT_EQ(300, game.getScore());
}

int main(int argc, char **argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}