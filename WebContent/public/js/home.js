$(function(){
    $('#search').click(function(event) {
        var basePath = $('#basePath').val();
        var key = $('#key').val();
        $.ajax({
            url: basePath+'/Search',
            type: 'POST',
            dataType: 'json',
            data: {key: key},
            success: function(data){
                console.log(data);
                var html = template('line-template', data);
                $('.content').html(html);
            },
            error: function(xhr){
                console.log(xhr);
            }
        });
    });

    $('#key').keypress(function(event) {
        var key = event.which;
        //console.log(key);
        if(key == 13){
            $('#search').trigger('click');
        }
    });
    
});