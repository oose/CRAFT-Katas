#ifndef STATEMACHINE_H_
#define STATEMACHINE_H_

#include <memory>
#include "../driver/BarrierActuator.h"
#include "../ui/display.h"

namespace domain {

class LevelCrossingState;

class StateMachine {
public:
	enum class StateID {
		OPENING, CLOSING, OPEN, CLOSED
	};

	StateMachine(driver::BarrierActuatorPtr actuator);

	void trainApproaching();
	void trainHasPassed();
	void barrierFullyOpened();
	void barrierFullyClosed();

	StateID getStateID();

private:
	StateID currentState;
	driver::BarrierActuatorPtr actuator;
	ui::Display display;
};

}

#endif /* STATEMACHINE_H_ */
