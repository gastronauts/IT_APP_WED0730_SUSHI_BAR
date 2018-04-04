export default function CartReducer(
    state = {
        sum: 0,
        itemsInCart: []
    },
    action
) {
    switch (action.type) {

        case "ADD_ITEM_TO_CART":
            let newSum = 0;
            let updateItem = false;
            let newState = state.itemsInCart.map( (item) => {
                if(item.mealId === action.payload.mealId){
                    updateItem = true;
                    newSum += (item.amount + action.payload.amount) * item.price;
                    return { ...item, amount: item.amount + action.payload.amount }
                }
                else{
                    newSum += item.amount * item.price;
                    return item
                }
            });


            state = {...state,
                sum: updateItem ? newSum : newSum + action.payload.amount * action.payload.price,
                itemsInCart: updateItem ? newState : [...state.itemsInCart, action.payload]
            };
            break;

        case "REMOVE_ITEM_FROM_CART":
            newSum = 0;
            newState = [];

            state.itemsInCart.forEach( (item) => {
                if(item.mealId !== action.payload.mealId){
                    newState.push(item);
                    newSum += item.amount * item.price
                }
            });

            state = {...state,
                sum: newSum,
                itemsInCart: newState
            };
            break;

        case "UPDATE_AMOUNT_OF_ITEM_IN_CART":
            newSum = 0;
            newState = state.itemsInCart.map( (item) => {
                if(item.mealId === action.payload.mealId){
                    newSum += action.payload.amount * item.price;
                    return { ...item, amount: action.payload.amount }
                }
                else{
                    newSum += item.amount * item.price;
                    return item
                }
            });

            state = {...state,
                sum: newSum,
                itemsInCart: newState
            };
            break;
        default:
            return state;
    }

    return state;
}