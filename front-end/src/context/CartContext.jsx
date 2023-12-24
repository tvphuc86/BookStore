import React, { createContext, useContext, useEffect, useState } from 'react'

const CartContext = createContext()


const CartProvider = ({ children }) => {

    const [cartItems, setCartItems] = useState(localStorage.getItem('cart') ? JSON.parse(localStorage.getItem('cart')) : [])
    const [cartQuality, setCartQuality] = useState(0)
    const [openCartModal, setOpenCartModal] = useState(false)

    const HandleOpenCart = () => {
        setOpenCartModal(true)
    }
    const HandleCloseCart = () => {
        setOpenCartModal(false)
    }

    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cartItems))
    }, [cartItems])


    useEffect(() => {
        setCartQuality(getQuantityCart())
    }, [cartItems])

    function addToCart(book) {
        const isBookInCart = cartItems.find((cartItem) => cartItem.book.id === book.id)
        if (isBookInCart) {
            setCartItems(cartItems.map((cartItem) =>
                cartItem.book.id === book.id
                    ? { ...cartItem, cart_item_quantity: cartItem.cart_item_quantity + 1 } : cartItem))

        }
        else {
            setCartItems([...cartItems, { cart_item_quantity: 1, book: book, selected:false }])
        }

    }

    function removeFromCart(book) {
        const isProductInCart = cartItems.find((cartItem) => cartItem.book.id === book.id)
        if(isProductInCart){
            if (isProductInCart.cart_item_quantity === 1) {
                setCartItems(cartItems.filter((cartItem) => cartItem.book.id !== book.id))
            }
            else {
                setCartItems(cartItems.map((cartItem) =>
                    cartItem.book.id === book.id ? { ...cartItem, cart_item_quantity: cartItem.cart_item_quantity - 1 } : cartItem))
            }
        }
        
    }
    const handleToggle = (itemId) => {
        setCartItems((prevItems) =>
          prevItems.map((item) =>
            item.book.id === itemId ? { ...item, selected: !item.selected } : item
          )
        );
      };

    function clearCartItem() {
        setCartItems([])
    }

    function removeBookInCart(book) {
        setCartItems(cartItems.filter((cartItem) => cartItem.book.id !== book.id))
    }

    function getCartTotal() {
        return cartItems.filter((item) => item.selected).reduce((total, item) =>
            total + item.book.book_price * item.cart_item_quantity
        , 0)
    }

    function getQuantityCart() {
        return cartItems.length
    }

    function checkAllCart(state){
        const updateCart = cartItems.map((item)=>({...item, selected: state}))
        setCartItems(updateCart)
    }


    return (
        <CartContext.Provider value={{ openCartModal, handleToggle,HandleOpenCart, HandleCloseCart, cartItems, removeBookInCart, cartQuality, addToCart, removeFromCart, getCartTotal, setCartItems, checkAllCart}}>{children}</CartContext.Provider>
    )
}

export const useCartContext = () => { return useContext(CartContext) }

export default CartProvider