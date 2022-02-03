'use strict'
//side bar menu
$(document).ready(function (){
    $.ajax({
        url: '/api/board/category',
        type: 'GET',
        dataType: 'JSON',
        async: false,
        success: function (result){
            console.log(result);
            $.each(result, function (idx, item){
                $('.categoryList').append(
                    "<li id='"
                    +item.categoryName+
                    "' class='cate'><a href=# class=\"link-dark rounded\" >"
                    +item.categoryName+
                    "</a></li>"
                )
            })
            $(document).on('click', '.cate' , function (){
                var ctgId = this.id
                console.log(ctgId);
                $.ajax({
                    url: '/api/board/list',
                    type: 'GET',
                    dataType: 'JSON',
                    data:{
                        ctg: ctgId
                    },
                    success: function (result){
                        // fn(result);
                        $('tbody').empty();
                        $.each(result, function (idx, cont){
                            $('tbody').append(
                                "<tr><td>"
                                + cont.idx +
                                "</td><td><a>"
                                + cont.title +
                                "</a></td><td>"
                                + cont.user.userId +
                                "</td><td>"
                                + cont.createdAt +
                                "</td></tr>"
                            )
                        })
                    },
                    error: function () {
                        alert("Message : list error")
                    }
                })
            })
            $.ajax({
                url: '/api/board/list',
                type: 'GET',
                dataType: 'JSON',
                data:{
                    ctg:"FREE_BOARD"
                },
                success: function (result){
                    console.log(result);
                    fn(result);

                },
                error: function () {
                    alert("Message : list error")
                }
            })
        },
        error: function () {
            alert("Message : error")
        }
    })
    function fn(b){
        $('tbody').empty();
        $.each(b, function (idx, cont){
            $('tbody').append(
                "<tr><td>"
                + cont.idx +
                "</td><td><a>"
                + cont.title +
                "</a></td><td>"
                + cont.user.userId +
                "</td><td>"
                + cont.createdAt +
                "</td></tr>"
            )
        })
    }
})