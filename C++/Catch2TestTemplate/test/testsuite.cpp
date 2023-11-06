#define CATCH_CONFIG_MAIN  // This tells Catch to provide a main() - only do this in one cpp file
#include "Catch.hpp"
#include "../src/sut.h"

TEST_CASE("testThatZeroIsInitialValue"){
	SUT sut{};
	REQUIRE(0 == sut.get());
}


