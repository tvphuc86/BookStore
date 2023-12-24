import axios, { all } from 'axios'
import React, { useEffect, useState } from 'react'
import { useCartContext } from '../../context/CartContext'
import BookCard from '../BookCard'





const PopularBook = () => {
    const category = [
        {
            title: 'Tất cả',
            value: 'all'
        },
        {
            title: 'Truyện Tranh - Manga',
            value: '1'
        },
        {
            title: 'Sách Kinh Tế',
            value: '2'
        },
        {
            title: 'Văn Học - Tiểu Thuyết',
            value: '3'
        },
        {
            title: 'Thường Thức - Đời Sống',
            value: '4'
        },
        {
            title: 'Kỹ Năng - Sống Đẹp',
            value: '5'
        }
    ]

    function shuffle(arra1) {
		var ctr = arra1.length, temp, index;

	// While there are elements in the array
		while (ctr > 0) {
	// Pick a random index
			index = Math.floor(Math.random() * ctr);
	// Decrease ctr by 1
			ctr--;
	// And swap the last element with it
			temp = arra1[ctr];
			arra1[ctr] = arra1[index];
			arra1[index] = temp;
		}
		return arra1;
	}




    const [categories, setCategories] = useState(category)
    const [data, setData] = useState([])
    const [dataFilter, setDataFilter] = useState([])
    const [active, setActive] = useState('all')

    const {addToCart} = useCartContext()

    useEffect(()=>{
        if(active === 'all')
            setDataFilter(data)
        else
        setDataFilter(data.filter((book)=>book.category.id === Number(active)))
    },[active])


    const HandleCategoryClick = (value) => {
        setActive(value)
    }

    useEffect(()=>{
        setDataFilter(data)
    },[data])

    const GetBook = async () => {
        axios.get('/api/books')
            .then((result) => {
                setData(shuffle(result.data))
        })
    }

    useEffect(() => {
        GetBook()
    }, [])

    return (
        <section id="popular-books" class="bookshelf py-5 my-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="section-header align-center">
                            <div class="title">
                                <span>Some quality items</span>
                            </div>
                            <h2 class="section-title mt-2">Sách nổi bật</h2>
                        </div>

                        <ul class="tabs">
                            {categories.map((category, index) => {
                                if (category.value === active)
                                    return <li data-tab-target="#all-genre" class="active tab" onClick={() => HandleCategoryClick(category.value)}>{category.title}</li>
                                else
                                    return <li data-tab-target="#all-genre" class="tab" onClick={() => HandleCategoryClick(category.value)}>{category.title}</li>
                            })}
                        </ul>

                        <div class="tab-content">
                            <div id="all-genre" data-tab-content className='active'>
                                <div class="row">
                                    {dataFilter.map((book) => {
                                        return (
                                           <BookCard book={book} />
                                        )
                                    })}
                                </div>

                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </section>
    )
}

export default PopularBook