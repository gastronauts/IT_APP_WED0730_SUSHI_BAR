import { NavigationActions } from "react-navigation";
import MainNavigator from "../routes/routes";

const initialNavState = MainNavigator.router.getStateForAction(
    NavigationActions.reset({
        index: 0,
        actions: [
            NavigationActions.navigate({
                routeName: "Menu"
            })
        ]
    })
);

export default (state = initialNavState, action) => {
    const nextState = MainNavigator.router.getStateForAction(action, state);
    return nextState || state;
};