
#include <iostream>
#include <assert.h>
#include <stdexcept>
#include "domain/StateMachine.h"
#include "driver/BarrierActuator.h"
#include "driver/BarrierActuatorStub.h"

using namespace std;
using StateID = domain::StateMachine::StateID;

int main() {
	try {
		driver::BarrierActuatorPtr actuator = make_shared<driver::BarrierActuatorStub>();
		domain::StateMachine stateMachine{actuator};
		assert(StateID::OPEN == stateMachine.getStateID());
		stateMachine.trainApproaching();
		assert(StateID::CLOSING == stateMachine.getStateID());
		stateMachine.barrierFullyClosed();
		assert(StateID::CLOSED == stateMachine.getStateID());
		stateMachine.trainHasPassed();
		assert(StateID::OPENING == stateMachine.getStateID());
		stateMachine.barrierFullyClosed();
	} catch (const domain_error& e){
		cerr << "Error occurred: " << e.what() << endl;
	}
}
