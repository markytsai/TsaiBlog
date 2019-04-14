addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
}, false);

function hideURLbar() {
    window.scrollTo(0, 1);
}

$(function () {
    $("#slider").responsiveSlides({
        auto: true,
        nav: true,
        speed: 500,
        namespace: "callbacks",
        pager: true,
    });
});

$(window).load(function () {

    $("#flexiselDemo3").flexisel({
        visibleItems: 5,
        animationSpeed: 1000,
        autoPlay: true,
        autoPlaySpeed: 3000,
        pauseOnHover: true,
        enableResponsiveBreakpoints: true,
        responsiveBreakpoints: {
            portrait: {
                changePoint: 480,
                visibleItems: 2
            },
            landscape: {
                changePoint: 640,
                visibleItems: 3
            },
            tablet: {
                changePoint: 768,
                visibleItems: 3
            }
        }
    });

});

$("span.menu").click(function () {
    $(".head-nav ul").slideToggle(300, function () {
        // Animation complete.
    });
});

// ------------------------返回顶部按钮----------------------------------------
// window.onscroll = function () {
//     scrollFunction()
// };
//
// function scrollFunction() {
//     if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
//         document.getElementById("myBtn").style.display = "block";
//     } else {
//         document.getElementById("myBtn").style.display = "none";
//     }
// }
//
// // When the user clicks on the button, scroll to the top of the document
// function topFunction(event) {
//
//     var target = '#myBtn';
//     $('html, body').animate({
//         scrollTop: 0
//     }, 1000);
//
// }
