#include "BowlingGame.h"
#include <stdexcept>

constexpr std::size_t BowlingGame::MAX_NUMBER_OF_ROLLS_PER_GAME { 21u };
constexpr unsigned short BowlingGame::HIGHEST_SCORE_PER_ROLL { 10 };

BowlingGame::BowlingGame() : _currentRoll { 0u } {
  _rolls.resize(MAX_NUMBER_OF_ROLLS_PER_GAME, 0);
}

void BowlingGame::roll(const unsigned short numberOfClearedPins) {
  if (numberOfClearedPins > HIGHEST_SCORE_PER_ROLL) {
    throw std::invalid_argument("It is not possible to clear more than 10 pins with one roll!");
  }
  _rolls.at(_currentRoll++) = numberOfClearedPins;
}

unsigned int BowlingGame::getScore() const {
  unsigned int score { 0u };
  Rolls::size_type rollIndex { 0u };

  for (auto frame = 0; frame < 10; frame++) {
    if (isStrike(rollIndex)) {
      score += HIGHEST_SCORE_PER_ROLL + _rolls.at(rollIndex + 1)
        + _rolls.at(rollIndex + 2);
      rollIndex++;
    } else if (isSpare(rollIndex)) {
      score += HIGHEST_SCORE_PER_ROLL + _rolls.at(rollIndex + 2);
      rollIndex += 2;
    } else {
      score += _rolls.at(rollIndex) + _rolls.at(rollIndex + 1);
      rollIndex += 2;
    }
  }
  return score;
}

bool BowlingGame::isSpare(const Rolls::size_type rollIndex) const {
  return (_rolls.at(rollIndex) + _rolls.at(rollIndex + 1) == HIGHEST_SCORE_PER_ROLL);
}

bool BowlingGame::isStrike(const Rolls::size_type rollIndex) const {
  return (_rolls.at(rollIndex) == HIGHEST_SCORE_PER_ROLL);
}
