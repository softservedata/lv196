/* Theme Name: Zuscon - Landing page Template
   Author: Zoyothemes
   Version: 1.0.0
   Created:July 2016
   File Description:Main JS file of the template
*/


$(window).load(function() {

    "use strict";

    /*---------------------------------------*/
    /*  LOADER
    /*---------------------------------------*/
    $('.status').fadeOut();
    $('.preloader').delay(350).fadeOut('slow');

    /*---------------------------------------*/
    /*  STICKY HEADER
    /*---------------------------------------*/
    $(".sticky").sticky({topSpacing: 0});

    /*---------------------------------------*/
    /*  STELLAR FOR BACKGROUND SCROLLING
    /*---------------------------------------*/

});


(function($) {

    "use strict";

    /* ==============================================
    Smooth Scroll To Anchor
    =============================================== */
    //jQuery for page scrolling feature - requires jQuery Easing plugin
    $(function() {
        $('.navbar-nav a').bind('click', function(event) {
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top - 0
            }, 1500, 'easeInOutExpo');
            event.preventDefault();
        });
    });

    /* ==============================================
    Magnific Popup
    =============================================== */
    $('.popup-video').magnificPopup({
      disableOn: 700,
      type: 'iframe',
      mainClass: 'mfp-fade',
      removalDelay: 160,
      preloader: false,
      fixedContentPos: false
    });

})(jQuery);





