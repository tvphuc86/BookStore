import React from 'react'

const Footer = () => {
    return (
        <><footer id="footer">
            <div class="container">
                <div class="row">

                    <div class="col-md-4">

                        <div class="footer-item">
                            <div class="company-brand">
                                <img src={process.env.PUBLIC_URL+"/logo_book_store.png"} alt="logo" class="footer-logo"/>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-2">

                        <div class="footer-menu">
                            <h5>Về chúng tôi</h5>
                            <ul class="menu-list">
                                <li class="menu-item">
                                    <a href="#">Tầm nhìn</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Bài viết</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Điều khoản dịch vụ</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Đóng góp</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-2">

                        <div class="footer-menu">
                            <h5>Khám phá</h5>
                            <ul class="menu-list">
                                <li class="menu-item">
                                    <a href="#">Trang chủ</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Sách</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Tác giả</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Chủ đề</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Tìm kiếm</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-2">

                        <div class="footer-menu">
                            <h5>Tài khoản</h5>
                            <ul class="menu-list">
                                <li class="menu-item">
                                    <a href="#">Đăng nhập</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Giỏ hàng</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Danh sách yêu thích</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Truy cập đơn hàng </a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-2">

                        <div class="footer-menu">
                            <h5>Hỗ trợ</h5>
                            <ul class="menu-list">
                                <li class="menu-item">
                                    <a href="#">Trung tâm trợ giúp</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Báo cáo vấn đề</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Mong muốn thay đổi</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Liên hệ</a>
                                </li>
                            </ul>
                        </div>

                    </div>

                </div>

            </div>
        </footer>

            <div id="footer-bottom">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="copyright">
                                <div class="row">

                                    <div class="col-md-6">
                                        <p>© Nhóm chúng tôi</p>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="social-links align-right">
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

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </>

    )
}

export default Footer