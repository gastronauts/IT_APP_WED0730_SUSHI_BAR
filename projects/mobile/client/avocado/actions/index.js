import axios from 'axios';

export const addItemToCart = (mealId, mealName, ingredients, price, amount, image, estimatedTime,details) => {
    return {
        type: 'ADD_ITEM_TO_CART',
        payload: {
            mealId: mealId,
            mealName: mealName,
            ingredients: ingredients,
            price: price,
            amount: amount,
            image: image,
            estimatedTime: estimatedTime,
            details: details
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

export const getCurrentMenu = () => {
    return {
        type: 'GET_CURRENT_MENU',
        payload: axios.get('http://sushi.mimosa-soft.com/menu/current')
    }
};

export const postOrder = (orderId, tableId, meals, price) => {
    return {
        type: 'POST_ORDER',
        payload: axios.post('http://sushi.mimosa-soft.com/order', {
            id: orderId,
            table: {
                id: tableId
            },
            meals: meals,
            status: 0,
            summaryPrice: price
        })
    }
};

export const setTableNumber = (tableNumber) => {
    return {
        type: 'SET_TABLE_NUMBER',
        payload: {
            tableNumber: tableNumber
        }
    }
};

export const getStatusesOfOrders = (tableNumber) => {
    return {
        type: 'GET_STATUSES_OF_ORDER',
        payload: axios.get('http://sushi.mimosa-soft.com/order/table/current/' + tableNumber)
    }
};