#ifndef SRC_SUT_H_
#define SRC_SUT_H_



class SUT {
public:
	void reset();

	void inc();

	int get();

	void hello();

private:
	int x = 0;
};
#endif /* SRC_SUT_H_ */
