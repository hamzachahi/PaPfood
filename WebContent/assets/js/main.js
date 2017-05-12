(function ($) {
  "use strict";

  /*----------------------------
  START - Scroll Bar for the menu
  ------------------------------ */
  $(".scrollbar-inner").niceScroll({cursorcolor:"#000",cursoropacitymax:0.7}); // First scrollable DIV
  // END - Scroll Bar for the menu

  /*----------------------------
  START - Toggle Hidden Sidebar
  ------------------------------ */
  var toggles = document.querySelectorAll(".c-hamburger");

  for (var i = toggles.length - 1; i >= 0; i--) {
    var toggle = toggles[i];
    toggleHandler(toggle);
  };

  function toggleHandler(toggle) {
    toggle.addEventListener( "click", function(e) {
      e.preventDefault();
      (this.classList.contains("is-active") === true) ? this.classList.remove("is-active") : this.classList.add("is-active");
    });
    return false;
  }
  // Hidden Sidebar
  $(document).on('click', '.c-hamburger', function() {
    if($("#hidden-sidebar").hasClass('hide-sidebar')) {
      $("#hidden-sidebar").removeClass('hide-sidebar').addClass('show-sidebar').fadeIn(30000);
      $( "body" ).append( "<div class=\"lt-tranparent\"></div>" );
    } else {
      $("#hidden-sidebar").removeClass('show-sidebar').addClass('hide-sidebar').fadeIn(30000);
      $( "div" ).remove( ".lt-tranparent" );
    }
    return false;
  });
  // END - Toggle Hidden Sidebar

  /*----------------------------
  START - mean menu
  ------------------------------ */
  $('header nav').meanmenu({
    meanScreenWidth: "991",
    meanMenuContainer: ".mobile-menu",
    meanRevealPosition: "left",
    right: "auto",
  });
  // END - mean menu

  /*----------------------------
  START - stiky menu
  ------------------------------ */
  $("#main-menu").sticky({ topSpacing: 0 });

  /*----------------------------
  START - flex slier for carousel
  ------------------------------ */
  $('#carousel').flexslider({
    animation: "slide",
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    itemWidth: 100,
    itemMargin: 10,
    asNavFor: '#slider'
  });
  // END - flex slier for carousel

  /*----------------------------
  START - flex slier for slider
  ------------------------------ */
  $('#slider').flexslider({
    animation: "slide",
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    sync: "#carousel",
    start: function(slider) {
      $('body').removeClass('loading');
    }
  });
  // END - flex slier for slider

  /*----------------------------
  START - flex slier for team slider
  ------------------------------ */
  $('#team-slider').flexslider({
    animation: "slide",
    controlNav: "thumbnails"
  });
  // END - flex slier for team slider

  /*----------------------------
  START - Slick carousel active
  ------------------------------ */
  $('.single-thumbnail-big').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
    fade: true,
    asNavFor: '.single-thumbnail-small'
  });
  $('.single-thumbnail-small').slick({
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 4,
    asNavFor: '.single-thumbnail-big',
    dots: false,
    centerMode: false,
    focusOnSelect: true,
    arrows: false,
    prevArrow: '<button type="button" class="custom-prev"><i class="fa fa-long-arrow-left"></i></button>',
    nextArrow:'<button type="button" class="custom-next"><i class="fa fa-long-arrow-right"></i></button>'
  });
  // END - Slick carousel active

  /*----------------------------
  START - slide Toggle catagory title
  ------------------------------ */
  $('.catagory-title').on('click', function() {
    $('.category-menu-list').slideToggle(500);
    return false;
  });
  // END - slide Toggle catagory title

  /*----------------------------
  START - Price Slider active
  ------------------------------ */
  if ($("#slider-range").length > 0) {
    $("#slider-range").slider({
      range: true,
      min: 0,
      max: 400,
      values: [12, 300],
      slide: function(event, ui) {
        $("#amount").val("Range: $" + ui.values[0] + " - $" + ui.values[1]);
      }
    });

    $("#amount").val("Range:  $" + $("#slider-range").slider("values", 0) +
    " - $" + $("#slider-range").slider("values", 1));
  }
  // END - Price Slider active

    
  /*----------------------------
  START - Nivo slider
  ------------------------------ */
  $('#ensign-nivoslider').nivoSlider({
    effect: 'slideInRight',
    slices: 15,
    boxCols: 8,
    boxRows: 4,
    animSpeed: 500,
    pauseTime: 5000,
    startSlide: 0,
    directionNav: true,
    controlNavThumbs: false,
    pauseOnHover: false,
    manualAdvance: true
  });

  $('#ensign-nivoslider-2').nivoSlider({
    effect: 'fade',
    slices: 15,
    boxCols: 8,
    boxRows: 4,
    animSpeed: 500,
    pauseTime: 5000,
    startSlide: 0,
    directionNav: true,
    controlNavThumbs: false,
    pauseOnHover: true,
    manualAdvance: false
  });

  $('#ensign-nivoslider-3').nivoSlider({
    effect: 'random',
    slices: 15,
    boxCols: 8,
    boxRows: 4,
    animSpeed: 500,
    pauseTime: 5000,
    startSlide: 0,
    directionNav: true,
    controlNavThumbs: false,
    pauseOnHover: true,
    manualAdvance: true
  });
  // END - Nivo slider

  /*----------------------------
  START - owlCarousel for product list
  ------------------------------ */  
  $(".product-list, .new-product-owl-active").owlCarousel({
    autoPlay: true,
    slideSpeed:2000,
    pagination:false,
    navigation:true,	  
    items : 4,
    /* transitionStyle : "fade", */    /* [This code for animation ] */
    navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
    itemsDesktop : [1199,4],
    itemsDesktopSmall : [980,3],
    itemsTablet: [768,2],
    itemsMobile : [479,1],
  });
  // END - owlCarousel for product list

  /*----------------------------
  START - isotop filter
  ------------------------------ */
  var $container = $('.GalleryContainer, .RestaurantMenuContainer');
  $container.isotope({
    filter: '*',
    animationOptions: {
      duration: 750,
      easing: 'linear',
      queue: false
    }
  });

  // Gallery Filter
  $('.GalleryFilter a').on('click', function() {
    $('.GalleryFilter .current').removeClass('current');
    $(this).addClass('current');

    var selector = $(this).attr('data-filter');

    $(".GalleryContainer").isotope({
      filter: selector,
      resizesContainer: false,
      animationOptions: {
        duration: 750,
        easing: 'linear',
        queue: false
      }
    });
    return false;
  });

  // Filter menu
  $('.RestaurantMenuFilter a').on('click', function() {
    $('.RestaurantMenuFilter .current').removeClass('current');
    $(this).addClass('current');

    var selector = $(this).attr('data-filter');

    $container.isotope({
      filter: selector,
      resizesContainer: false,
      animationOptions: {
        duration: 750,
        easing: 'linear',
        queue: false
      }
    });
    return false;
  });

  // Masonry Filter
  var $grid = $('.MasonryContainer').imagesLoaded( function() {
    $grid.isotope({
      itemSelector: '.col-md-4',
      percentPosition: true,
      masonry: {
        gutter: 0,
        columnWidth: '.grid-sizer',
        columnWidth: 0
      }
    });
  });

  $('.MasonryFilter a').on('click', function() {
    $('.MasonryFilter .current').removeClass('current');
    $(this).addClass('current');

    var selector = $(this).attr('data-filter');

    $(".MasonryContainer").isotope({
      filter: selector,
      resizesContainer: false,
        animationOptions: {
        duration: 750,
        easing: 'linear',
        queue: false
      }
    });
    return false;
  });
  // END - isotop filter
})(jQuery);
// END jQuery