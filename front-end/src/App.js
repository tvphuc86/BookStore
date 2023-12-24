import React from "react";
import { createBrowserRouter, Outlet, RouterProvider } from 'react-router-dom'
import NavBar from "./components/NavBar/NavBar";
import Home from "./site/Home";
import Footer from "./components/Footer";
import CartProvider from "./context/CartContext";
import Book from "./site/Book";
import BookDetails from "./site/BookDetails";
import CartModal from "./components/CartModal";
import Checkout from "./site/Checkout";
import PaymentInfo from "./components/PaymentInfo";


const SiteLayout = () => {
  return (
    <>
      <CartProvider>
        <NavBar />
        <Outlet />
        <Footer />
        <CartModal />
      </CartProvider>

    </>
  )
}


const router = createBrowserRouter([
  {
    path: '/',
    element: <SiteLayout />,
    children: [
      {
        path: '',
        element: <Home></Home>
      },
      {
        path: '/books',
        element: <Book/>
      },
      {
        path: '/books/:id',
        element: <BookDetails/>
      },
      {
        path: '/checkout',
        element: <Checkout/>
      },
      {
        path: '/payment-info',
        element: <PaymentInfo/>
      },
    ]
  }
])

function App() {

  return (
    <RouterProvider router={router} />
  )
}

export default App;
