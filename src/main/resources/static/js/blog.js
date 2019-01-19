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

$("span.menu").click(function () {
    $(".head-nav ul").slideToggle(300, function () {
        // Animation complete.
    });
});