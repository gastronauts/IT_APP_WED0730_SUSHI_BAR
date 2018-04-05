
export const addItemToCart = (mealId, mealName, ingredients, price, amount, image, estimatedTime) => {
    return {
        type: 'ADD_ITEM_TO_CART',
        payload: {
            mealId: mealId,
            mealName: mealName,
            ingredients: ingredients,
            price: price,
            amount: amount,
            image: image,
            estimatedTime: estimatedTime
        }
    }
};

export const removeItemFromCart = (mealId) => {
    return {
        type: 'REMOVE_ITEM_FROM_CART',
        payload: {
            mealId: mealId
        }
    }
};

export const updateAmountOfItemInCart = (mealId, amount) => {
    return {
        type: 'UPDATE_AMOUNT_OF_ITEM_IN_CART',
        payload: {
            mealId: mealId,
            amount: amount
        }
    }
};

export const addOrder = (orderId, meals, status, sum, time) => {
    return {
        type: 'ADD_ORDER',
        payload: new Promise( resolve => {
            resolve({
                orderId: orderId,
                meals: meals,
                status: status,
                sum: sum,
                estimatedTime: time
            });
        })
    }
};

export const emptyCart = () => {
    return {
        type: 'EMPTY_CART',
        payload: {

        }
    }
};