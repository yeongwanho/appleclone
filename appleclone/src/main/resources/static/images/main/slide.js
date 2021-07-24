var slides = document.getElementsByClassName('slides');
var dots =  document.getElementsByClassName('dots');

var slideIndex = 0;

document.getElementById('allSlide').innerHTML = slides.length;



var myVar;


showSlides();

function showSlides(){
    for(var i=0; i<slides.length; i++){
        slides[i].style.display = 'none';
        dots[i].className = dots[i].className.replace(" on", "");}
    
    slideIndex++;

    if(slideIndex > slides.length){slideIndex = 1;}

    document.getElementById('thisSlide').innerHTML = slideIndex;
    slides[slideIndex-1].style.display = 'block';
    dots[slideIndex-1].className += ' on';
    
    myVar = setTimeout(showSlides, 2000);
}//showSlides

function stopSlide(){ clearTimeout(myVar);} //stopSlide

/* ========= */

function prevNext(num){
    stopSlide();
    slideIndex += num;

    for(var i=0; i<slides.length; i++){
        slides[i].style.display = 'none';
        dots[i].className = dots[i].className.replace(" on", ""); }

    if(slideIndex < 1){ slideIndex = slides.length; }
    if(slideIndex > slides.length ){ slideIndex = 1; }

    document.getElementById('thisSlide').innerHTML = slideIndex;
    slides[slideIndex - 1 ].style.display = 'block';
    dots[slideIndex - 1 ].className += ' on';
}

/* ========= */
function currentSlide(num){
    stopSlide();
    slideIndex = num;

    for(var i=0; i<slides.length; i++){
        slides[i].style.display = 'none';
        dots[i].className = dots[i].className.replace(" on", ""); }
    
    document.getElementById('thisSlide').innerHTML = slideIndex;
    slides[slideIndex - 1 ].style.display = 'block';
    dots[slideIndex - 1 ].className += ' on';

}