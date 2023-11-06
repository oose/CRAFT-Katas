#ifndef BARRIERACTUATOR_H_
#define BARRIERACTUATOR_H_

#include <memory>

namespace driver {

class BarrierActuator {
public:
	virtual void sendCloseCommand() = 0;
	virtual void sendOpenCommand() = 0;
	virtual ~BarrierActuator() = default;
};

using BarrierActuatorPtr = std::shared_ptr<BarrierActuator>;

}

#endif /* BARRIERACTUATOR_H_ */
