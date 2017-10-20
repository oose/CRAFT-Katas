#include "../src/StringCalculator.h"
#include <gtest/gtest.h>

TEST(StringCalculatorTestCase, AnEmptyStringReturnsZero ) {
	StringCalculator calculator;
	ASSERT_EQ(0, calculator.calculateSumOf(""));
}

TEST(StringCalculatorTestCase, ValidStringWithoutNumbersReturnsZero ) {
	StringCalculator calculator;
	ASSERT_EQ(0, calculator.calculateSumOf(",,"));
}

TEST(StringCalculatorTestCase, ValidStringWithOneNumbersReturnsCorrectResult ) {
	StringCalculator calculator;
	ASSERT_EQ(42, calculator.calculateSumOf("42"));
}

TEST(StringCalculatorTestCase, ValidStringWithTwoNumbersReturnsCorrectResult ) {
  StringCalculator calculator;
  ASSERT_EQ(42, calculator.calculateSumOf("12,30"));
}

TEST(StringCalculatorTestCase, ValidStringWithThreeNumbersReturnsCorrectResult ) {
  StringCalculator calculator;
  ASSERT_EQ(342, calculator.calculateSumOf("12,30,300"));
}

TEST(StringCalculatorTestCase, InvalidStringWithNegativeNumberThrowsException ) {
  StringCalculator calculator;
  ASSERT_THROW(calculator.calculateSumOf("-12,30,300"), std::invalid_argument);
}

TEST(StringCalculatorTestCase, WhitespaceCharactersAreAllowedInNumberString ) {
  StringCalculator calculator;
  ASSERT_EQ(342, calculator.calculateSumOf("12,  30, \n  300"));
}

TEST(StringCalculatorTestCase, OtherDelimitersThanCommasAreNotAllowedInNumberString ) {
  StringCalculator calculator;
  ASSERT_THROW(calculator.calculateSumOf("12;  30/ \n  300"), std::invalid_argument);
}

