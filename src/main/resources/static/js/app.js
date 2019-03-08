(function ($) {
    'use strict';

    $(function () {
        var $fullText = $('.admin-fullText');
        $('#admin-fullscreen').on('click', function () {
            $.AMUI.fullscreen.toggle();
        });

        $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function () {
            $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
        });
    });
})(jQuery);


$('#doc-vld-name-2').keydown(function (event) {
    var key = event.keyCode;
    if (key >= 48 && key <= 90) {
        var val = $('#doc-vld-name-2').val();
        console.log(val)
        $.get("/interfaceSearch/"+val, function(data){
            console.log(data)
        });
    }
});
