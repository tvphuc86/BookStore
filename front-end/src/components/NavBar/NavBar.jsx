import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { useCartContext } from '../../context/CartContext'
import { Link } from 'react-router-dom'

const NavBar = () => {

  const {HandleOpenCart, cartQuality} = useCartContext()

  return (
    <div id="header-wrap">

      <div class="top-content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6">
              <div class="social-links">
                <ul>
                  <li>
                    <a href="#"><i class="icon icon-facebook"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="icon icon-twitter"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="icon icon-youtube-play"></i></a>
                  </li>
                  <li>
                    <a href="#"><i class="icon icon-behance-square"></i></a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="col-md-6">
              <div class="right-element">
                <a href="#" class="user-account for-buy"><i
                  class="icon icon-user"></i></a>
                <a onClick={()=>HandleOpenCart()} className="cart for-buy hover:cursor-pointer relative"><i class="fas fa-shopping-cart"></i><span className=' text-white bg-red-400 p-[5px] rounded-full absolute -translate-x-1 text-[8px]'>{cartQuality}</span></a>

                <div class="action-menu">

                  <div class="search-bar">
                    <a href="#" class="search-button search-toggle" data-selector="#header-wrap">
                      <i class="icon icon-search"></i>
                    </a>
                    <form role="search" method="get" class="search-box">
                      <input class="search-field text search-input" placeholder="Search"
                        type="search" />
                    </form>
                  </div>
                </div>

              </div>
            </div>

          </div>
        </div>
      </div>
      <header id="header">
        <div class="container-fluid">
          <div class="row">

            <div class="col-md-2">
              <div class="main-logo">
                <a href="index.html"><img className=' h-20 w-20' src={process.env.PUBLIC_URL + "/logo_book_store.png"} alt="logo" /></a>
              </div>

            </div>

            <div class="col-md-10">

              <nav id="navbar">
                <div class="main-menu stellarnav">
                  <ul class="menu-list">
                    <li class="menu-item active"><Link to="/">Trang chủ</Link></li>
                    <li class="menu-item has-sub">
                      <a class="nav-link">Pages</a>
                      <ul>
                        <li class="active"><a href="index.html">Trang chủ</a></li>
                        <li><a href="styles.html">Style</a></li>
                        <li><a href="blog.html">Blog</a></li>
                        <li><a href="about.html">About</a></li>
                      </ul>
                    </li>
                    <li class="menu-item"><Link to="/books" class="nav-link">Sách</Link></li>
                  </ul>

                  <div class="hamburger">
                    <span class="bar"></span>
                    <span class="bar"></span>
                    <span class="bar"></span>
                  </div>

                </div>
              </nav>

            </div>

          </div>
        </div>
      </header>

    </div>

  )
}

export default NavBar