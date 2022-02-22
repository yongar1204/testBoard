$(document).ready(function(){
    cateList()

    $(document).off('click', '.categoryAdd').on('click', '.categoryAdd' , function (){
        var categoryName = $('#categoryInput').val();
        console.log(categoryName)
        if (confirm('추가하시겠습니까?')){
            $.ajax({
                url: '/api/admin/cAdd',
                data: {
                    catName: categoryName
                },
                dataType: 'JSON',
                type: 'POST',
                success: cateList(),
            })
        } else {
            return false;
        }
    })

    $('.menuManage').click(function (){
        cateList();
    })

    $('.userManage').click(function (){
        $('#managePart').empty();
        $.ajax({
            url: '/api/admin/user',
            type: 'GET',
            dataType: 'JSON',
            success: function (result) {
                console.log(result)
                $('#managePart').append(
                    "<ul class='userL'></ul>"
                )
                $.each(result, function (idx, item) {
                    console.log(item)
                    $('.userL').append(
                        "<li><a id='"
                        + item.userId +
                        "' href=# class=\"link-dark rounded cate\">"
                        + item.userId +
                        "</a></li>"
                    )
                })
            }
        })
    })

    function cateList(){
        $.ajax({
            url: '/api/admin/category',
            type: 'GET',
            dataType: 'JSON',
            success: function (result){

                $('.isCategoryList').empty();
                $.each(result, function (idx, item) {
                    $('.isCategoryList').append(
                        "<li><a id='"
                        + item +
                        "' href=# class=\"link-dark rounded cate\">"
                        + item +
                        "</a></li>"
                    )
                    $('#categoryInput').val('추가할 카테고리는?')
                })
            },
            // error: console.log('getErr')
        })
    }
    $('#categoryDelete').click(function (){
        let categoryDelete = prompt('삭제할 카테고리 이름을 정확하게 입력하세요', []);
        console.log(categoryDelete)
        $.ajax({
            url:'/api/admin/deleteC',
            type: 'DELETE',
            data: {
                catName: categoryDelete
            },
            success : function (){
                alert("해당 카테고리가 삭제되었습니다.")
                cateList()
            }
        })
    })
})

