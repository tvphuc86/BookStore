import React, { useEffect, useState } from 'react'
import { Modal, Backdrop } from '@mui/material'
import { useCartContext } from '../context/CartContext'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'



const CartModal = () => {
  const { openCartModal, HandleCloseCart, cartItems, removeFromCart, addToCart, removeBookInCart, handleToggle, getCartTotal,checkAllCart } = useCartContext()

  const [cartTotal, setCartTotal] = useState(0)
  const [stateCheckBox, setStateCheckBox] = useState(false)

  const navigate = useNavigate()
  const checkStateCheckBoxAll = ()=>{
    cartItems.map((item)=>{
      if(item.selected === false){
        setStateCheckBox(false)
        return
      }
    })
  }

  const handleCheckAllChange = (e)=>{
    setStateCheckBox(e.target.checked)
    checkAllCart(e.target.checked)
  }


  useEffect(() => {
    setCartTotal(getCartTotal())
    checkStateCheckBoxAll()
  }, [cartItems])

  const checkoutVnpay = () =>{
    const totalPay = cartTotal+(cartTotal*5/100);
    axios.get(`http://localhost:8080/payments/vnpay?vnp_Amount=${totalPay}`)
    .then(rs => {
      localStorage.removeItem('cart')
      rs.data.status == "seccessfully" ?  window.location.replace(rs.data.url) : alert("Failed")
  
  })
  }
  return (
    <div>
      <Modal
        sx={{ border: 'none' }}
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={openCartModal}
        onClose={HandleCloseCart}
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            timeout: 500,
          },
        }}
      >
        <div in={openCartModal} className='absolute w-screen h-screen pt-[100px]'>
          <div className="bg-gray-100 py-8 w-[80%] h-[90%] m-auto">
            <div class="container mx-auto px-4 ">
              <h1 class="text-2xl font-semibold mb-4 font-kantit">Giỏ hàng</h1>
              <div class="flex flex-col md:flex-row gap-4">
                <div class="md:w-3/4">
                  <div class="bg-white rounded-lg shadow-md p-6 mb-4">
                    <div className=''>
                      <div className='grid grid-cols-8 gap-2'>
                        <span className=' col-span-3 font-semibold text-black'>Sản phẩm</span>
                        <span className=' font-semibold text-black'>Giá</span>
                        <span className=' col-span-2 font-semibold text-black'>Số lượng</span>
                        <div className=' font-semibold text-black'>Tổng</div>
                        <div className=' flex flex-col'>
                          <span>All</span>
                          <input type='checkbox' checked={stateCheckBox} onChange={(e)=>handleCheckAllChange(e)} className=' w-4 h-4'></input>
                        </div>
                      </div>
                      <div className='h-[380px] overflow-y-auto'>
                        {cartItems.map((item) => {
                          return (
                            <div key={item.book.id} className=' mt-4'>
                              <div className='grid grid-cols-8 gap-2'>
                                <div class="flex items-center col-span-3 gap-4">
                                  <img class=" w-28 object-fit" src={`data:image/jpeg;base64,${item.book.book_image}`} alt="Product image" />
                                  <span class=" font-semibold text-gray-800">{item.book.book_name}</span>
                                </div>
                                <span className='my-auto'>{item.book.book_price.toLocaleString()} đ</span>
                                <div class="flex items-center col-span-2">
                                  <button onClick={()=> removeFromCart(item.book)} className="border border-gray-900 rounded-full w-fit h-fit px-3 py-2 hover:bg-background-template">-</button>
                                  <span class="text-center w-8">{item.cart_item_quantity}</span>
                                  <button onClick={()=> addToCart(item.book)} className="border border-gray-900 rounded-full w-fit h-fit px-3 py-2 hover:bg-background-template">+</button>
                                </div>
                                <span className=' my-auto'>{(item.book.book_price * item.cart_item_quantity).toLocaleString()} đ</span>
                                <input checked={item.selected} onChange={()=>handleToggle(item.book.id)} type='checkbox' className='block my-auto w-4 h-4'></input>
                              </div>

                            </div>

                          )
                        })}

                      </div>

                    </div>
                  </div>
                </div>
                <div class="md:w-1/4">
                  <div class="bg-white rounded-lg shadow-md p-6">
                    <h2 class="text-lg font-semibold mb-4">Tổng</h2>
                    <div class="flex justify-between mb-2">
                      <span>Tổng tiền sản phẩm:</span>
                      <span>{cartTotal.toLocaleString()} đ</span>
                    </div>
                    <div class="flex justify-between mb-2">
                      <span>Thuế:</span>
                      <span>{(cartTotal*5/100).toLocaleString()} đ</span>
                    </div>
                    <div class="flex justify-between mb-2">
                      <span>Ship:</span>
                      <span>$0.00</span>
                    </div>
                    <hr class="my-2" />
                    <div class="flex justify-between mb-2">
                      <span class="font-semibold">Total:</span>
                      <span class="font-semibold">{(cartTotal+(cartTotal*5/100)).toLocaleString()} đ</span>
                    </div>
                    <button class="bg-blue-500 text-white py-2 px-4 rounded-lg mt-4 w-full" onClick ={()=> checkoutVnpay()} >Checkout with VNPay</button>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </Modal>
    </div>
  )

}

export default CartModal