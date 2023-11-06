#include <gtest/gtest.h>
#include "../src/sut.h"

struct Fixture : ::testing::Test {
	SUT sut;

	virtual void SetUp(){
		//init
	}

	virtual void TearDown(){
		//release
	}
};


TEST(suite, testThatZeroIsInitialValue){
	SUT sut{};
	ASSERT_EQ(0, sut.get()) << "Initial value should be 0";

}


TEST(suite, testThatResetWorks){
	SUT sut;
	sut.inc();
	sut.inc();
	sut.reset();
	ASSERT_EQ(0, sut.get()) << "After reset value should be 0";
}

TEST_F(Fixture, fixtureTest){
	ASSERT_EQ(0, sut.get());
}


int main(int argc, char** argv){
	::testing::InitGoogleTest(&argc, argv);
	return RUN_ALL_TESTS();
}
