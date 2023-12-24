import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import './PaymentInfoPage.css';


export default function PaymentInfo() {

    const navigate = useNavigate()
    const params = new URLSearchParams(window.location.search);
    const vnp_ResponseCode = params.get('vnp_ResponseCode');
    const vnp_Amount = params.get('vnp_Amount');
    const vnp_OrderInfo = params.get('vnp_OrderInfo');
    const vnp_TransactionNo = params.get('vnp_TransactionNo');
    const vnp_BankCode = params.get('vnp_BankCode');

    

    // Sử dụng giá trị trong component
  return (
    <>
    {vnp_ResponseCode == "00" ? 
    <>
     <h2 className="payment-info-title">Chi tiết thanh toán</h2>
      <table className="payment-info-table">
        <tbody>
          <tr>
            <td>Mã giao dịch:</td>
            <td>{vnp_TransactionNo}</td>
          </tr>
          <tr>
            <td>Giá trị thanh toán:</td>
            <td>{vnp_Amount}</td>
          </tr>
          <tr>
            <td>Tên giao dịch:</td>
            <td>{vnp_OrderInfo}</td>
          </tr>
          <tr>
            <td>Mã ngân hàng:</td>
            <td>{vnp_BankCode}</td>
          </tr>
        </tbody>
      </table>
    </> :  <h2 className="payment-info-title">Loi Thanh Toan</h2>}
    </>
  )
}
