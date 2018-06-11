export default function MenuReducer(
    state = {
        menu: []
    },
    action
) {
    switch (action.type) {

        case 'GET_CURRENT_MENU_FULFILLED':
            let newMenu = action.payload.data.meals.map( (item) => {
                return {
                    mealId: item.id,
                    mealName: item.name,
                    category: item.category,
                    details: item.details,
                    image: item.image,
                    estimatedTime: item.properTime,
                    ingredients: item.ingredients.map( (ing) => { return ing.name} ),
                    price: parseFloat(item.price).toFixed(2),
                    possibleToDo: item.possibleToDo
                }
            });
            state = {...state,
                menu: newMenu
            };
            break;

        default:
            return state;
    }

    return state;
}