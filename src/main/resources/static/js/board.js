'use strict'
//side bar menu and list
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
                        func(result);
                        $(document).on('click', '.pn' , function (){
                            var pn = this.id
                            console.log(pn)
                            $.ajax({
                                url: '/api/board/list',
                                type: 'GET',
                                dataType: 'JSON',
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
                    }
                })
            })
            $.ajax({
                url: '/api/board/list',
                type: 'GET',
                dataType: 'JSON',
                success: function (result){
                    console.log(result);
                    func(result);
                    $(document).on('click', '.pn' , function (){
                        var pn = this.id
                        console.log(pn)
                        $.ajax({
                            url: '/api/board/list',
                            type: 'GET',
                            dataType: 'JSON',
                            data:{
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
                }
            })
        },
        error: function () {
            alert("Message : error")
        }
    })
})
function func(result){
    var preId = result.paging.pageNum -1
    var nextId = result.paging.pageNum +1
    console.log(preId);
    $('tbody').empty();
    $('.pagination').empty();
    $('.pagination').append("<li id="+preId+" class='pn'><a th:href='#'>Previous</a></li>")
    for (var i=result.paging.startPage; i<=result.paging.lastPage; i++){
        $('.pagination').append("<li id='"+i+
            "' class='pn'" +i+
            "><a th:href='/api/board/test?pn=" +i+
            "'>" + i +"</a></li>")

    }
    $('.pagination').append("<li id="+nextId+" class='pn'><a th:href='#'>Next</a></li>")
    $.each(result.pagingList, function (idx, cont){
        console.log(cont.title)
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
