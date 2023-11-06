#ifndef STRINGCALCULATOR_H_
#define STRINGCALCULATOR_H_

#include <boost/tokenizer.hpp>
#include <string>
#include <vector>
#include <string_view>

using Numbers = std::vector<int>;
using Tokenizer = boost::tokenizer<boost::char_separator<char>>;

class StringCalculator {
public:
	int calculateSumOf(std::string_view input) const;

private:
  void checkNumberStringForIllegalCharacters(std::string_view commaSeparatedNumberString) const;
  Tokenizer tokenizeNumberString(std::string_view commaSeparatedNumberString) const;
  Numbers transformTokensToNumbers(const Tokenizer& tokenizedNumberString) const;
  int buildSumOfNumbers(const Numbers& numbers) const;

	static const std::string ALLOWED_CHARACTERS_FOR_NUMBERSTRING;
};

#endif // STRINGCALCULATOR_H_
