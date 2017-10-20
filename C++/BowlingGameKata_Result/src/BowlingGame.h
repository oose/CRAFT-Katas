#ifndef BOWLINGGAME_H_
#define BOWLINGGAME_H_

#include <vector>

using Rolls = std::vector<unsigned short>;

class BowlingGame {
public:
  BowlingGame();
  void roll(const unsigned short numberOfClearedPins);
  unsigned int getScore() const;

private:
  bool isStrike(const Rolls::size_type rollIndex) const;
  bool isSpare(const Rolls::size_type rollIndex) const;

  Rolls _rolls;
  Rolls::size_type _currentRoll;
  static const std::size_t MAX_NUMBER_OF_ROLLS_PER_GAME;
  static const unsigned short HIGHEST_SCORE_PER_ROLL;
};

#endif /* BOWLINGGAME_H_ */
