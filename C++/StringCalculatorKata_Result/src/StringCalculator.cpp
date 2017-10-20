#include "StringCalculator.h"
#include <algorithm>
#include <numeric>
#include <stdexcept>

using Delimiter = boost::char_separator<char>;

const std::string StringCalculator::ALLOWED_CHARACTERS_FOR_NUMBERSTRING = " \n\t,0123456789";

int StringCalculator::calculateSumOf(std::experimental::string_view commaSeparatedNumberString) const {
  checkNumberStringForIllegalCharacters(commaSeparatedNumberString);
  Tokenizer tokenizedNumberString = tokenizeNumberString(commaSeparatedNumberString);
  Numbers numbers = transformTokensToNumbers(tokenizedNumberString);
  return buildSumOfNumbers(numbers);
}

void StringCalculator::checkNumberStringForIllegalCharacters(
    std::experimental::string_view commaSeparatedNumberString) const {
  if (commaSeparatedNumberString.find_first_not_of(
      ALLOWED_CHARACTERS_FOR_NUMBERSTRING) != std::string::npos) {
    throw std::invalid_argument("Number string contains illegal characters.");
  }
}

Tokenizer StringCalculator::tokenizeNumberString(std::experimental::string_view commaSeparatedNumberString) const {
  Delimiter allowedDelimiter(",");
  Tokenizer tokenizedNumberString(commaSeparatedNumberString, allowedDelimiter);
  return tokenizedNumberString;
}

Numbers StringCalculator::transformTokensToNumbers(const Tokenizer& tokenizedNumberString) const {
  Numbers numbers;
  std::transform(begin(tokenizedNumberString),
                 end(tokenizedNumberString),
                 std::back_inserter(numbers),
                 [](const auto& numberString) -> int {
    int number = std::stoi(numberString);
    if (number < 0) {
      throw std::invalid_argument("Negative numbers are not allowed.");
    }
    return number;
  });
  return numbers;
}

int StringCalculator::buildSumOfNumbers(const Numbers& numbers) const {
  return std::accumulate(begin(numbers), end(numbers), 0);
}
