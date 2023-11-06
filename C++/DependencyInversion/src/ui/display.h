#ifndef UI_DISPLAY_H_
#define UI_DISPLAY_H_

#include <string>

namespace ui{

class Display {
public:
	virtual ~Display();
	virtual void displayMessage(const std::string&);
};

}
#endif /* UI_DISPLAY_H_ */
