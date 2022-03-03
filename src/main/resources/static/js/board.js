'use strict'
//side bar menu and list
//이벤트 중복 호출, 스텍처럼 쌓이는 문제.
$(document).ready(function (){
    $.ajax({
        url: '/api/board/category',
        type: 'GET',
        dataType: 'JSON',
        cache: false,
        async: false,
        success: function (result){
            $.each(result, function (idx, item){
                $('.categoryList').append(
                    "<li><a id='"
                    +item.categoryName+
                    "' href=# class=\"link-dark rounded cate\">"
                    +item.categoryName+
                    "</a></li>"
                )
            })

            $(document).off('click', '.cate').on('click', '.cate' , function (){
                var ctgId = this.id
                console.log(ctgId)
                $.ajax({
                    url: '/api/board/list',
                    type: 'GET',
                    dataType: 'JSON',
                    cache: false,
                    data:{
                        ctg: ctgId
                    },
                    success: function (result){
                        func(result)
                        console.log(result)
                        $('.totalCount').empty();
                        $('.totalCount').append(
                            "<label for='totalC'>Total Count : </label>\n" +
                            "<div id='totalC'>" + result.totalC + "</div>"
                        )
                        $(document).off('click', '.pn').on('click', '.pn' , function (){
                            var pn = this.id
                            console.log(ctgId)
                            $.ajax({
                                url: '/api/board/list',
                                type: 'GET',
                                dataType: 'JSON',
                                cache: false,
                                data:{
                                    ctg: ctgId,
                                    pn: pn
                                },
                                success: function (data){
                                    console.log(data)
                                    func(data)
                                },
                                error:function (){
                                    alert("Message : error")
                                },
                                async: false,
                            })
                        })
                    },
                    error: function () {
                        alert("Message : list error")
                    },
                    async: false
                })
            })
            $.ajax({
                url: '/api/board/test',
                type: 'GET',
                dataType: 'JSON',
                cache : false,
                success: function (result){
                    console.log(result)
                    func(result);
                    $('.totalCount').empty();
                    $('.totalCount').append(
                        "<label for='totalC'>Total Count : </label>\n" +
                        "<div id='totalC'>" + result.totalC + "</div>"
                    )
                    $(document).on('click', '.pn' , function (){
                        var pn = this.id
                        $.ajax({
                            url: '/api/board/test/'+pn,
                            type: 'GET',
                            dataType: 'JSON',
                            cache: false,
                            // data:{
                            //     pn: pn
                            // },
                            success: function (data){
                                console.log(data)
                                func(data)
                            },
                            error:function (){
                                alert("Message : error")
                            },
                            async: false,
                        })
                    })
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

    function func(result){
        var preId = result.paging.pageNum -1
        var nextId = result.paging.pageNum +1
        $('tbody').empty();
        $('.pagination').empty();
        $('.pagination').append("<li id="+preId+" class='pn pre'><a th:href='#'>Previous</a></li>")
        for (var i=result.paging.startPage; i<=result.paging.lastPage; i++){
            $('.pagination').append("<li id='"+i+
                "' class='pn'" +i+
                "><a th:href ='#'>" + i +"</a></li>")

        }
        $('.pagination').append("<li id="+nextId+" class='pn next'><a th:href='#'>Next</a></li>")

        var curpn = document.getElementById(result.paging.pageNum)
        var prepn = document.getElementById(0)
        var nextpn = document.getElementById(result.paging.totalPages + 1)
        if (curpn){
            curpn.classList.add('disabled')
        }
        if (prepn){
            prepn.classList.add('disabled')
        }
        if (nextpn){
            nextpn.classList.add('disabled')
        }

        $.each(result.pagingList, function (idx, cont){
            $('tbody').append(
                "<tr><td>"
                + cont.idx +
                "</td><td><a href='/board/detail?idx="+cont.idx+"'>"
                + cont.title +
                "</a></td><td>"
                + cont.user.userId +
                "</td><td>"
                + cont.createdAt +
                "</td><td>"
                + cont.hit +
                "</td></tr>"
            )
        })
    }
})
