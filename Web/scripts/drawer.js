/**
 * Created by Commander Data on 11/10/2017.
 */
function openNav() {
    document.getElementById("drawer").style.width = "235px";
    document.getElementById("tableContent").style.marginLeft = "235px";
    document.getElementById("open").style.visibility = "hidden";
    resizeHeaders();
}

function closeNav() {
    document.getElementById("drawer").style.width = "0";
    document.getElementById("tableContent").style.marginLeft= "0";
    document.getElementById("open").style.visibility = "visible";
    resizeHeaders();
}

// $(window).on('scroll', function () {
//     var scrollTop = $(window).scrollTop();
//     if (scrollTop == 0) {
//         $('#drawer').stop().animate({height: "100%"},200);
//     }
//     else {
//         $('#drawer').stop().animate({height: "150%"},200);
//     }
// });