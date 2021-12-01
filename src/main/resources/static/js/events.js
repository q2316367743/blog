/* global Fluid */

HTMLElement.prototype.wrap = function (wrapper) {
    this.parentNode.insertBefore(wrapper, this);
    this.parentNode.removeChild(this);
    wrapper.appendChild(this);
};

Fluid.events = {

    registerNavbarEvent: function () {
        let navbar = jQuery('#navbar');
        let submenu = jQuery('#navbar .dropdown-menu');
        if (navbar.offset().top > 0) {
            navbar.removeClass('navbar-dark');
            submenu.removeClass('navbar-dark');
        }
        Fluid.utils.listenScroll(function () {
            navbar[navbar.offset().top > 50 ? 'addClass' : 'removeClass']('top-nav-collapse');
            submenu[navbar.offset().top > 50 ? 'addClass' : 'removeClass']('dropdown-collapse');
            if (navbar.offset().top > 0) {
                navbar.removeClass('navbar-dark');
                submenu.removeClass('navbar-dark');
            } else {
                navbar.addClass('navbar-dark');
                submenu.removeClass('navbar-dark');
            }
        });
        jQuery('#navbar-toggler-btn').on('click', function () {
            jQuery('.animated-icon').toggleClass('open');
            jQuery('#navbar').toggleClass('navbar-col-show');
        });
    },

    registerParallaxEvent: function () {
        let bg = jQuery('#banner[parallax="true"]');
        if (bg.length === 0) {
            return;
        }
        let board = jQuery('#board');
        if (board.length === 0) {
            return;
        }
        let parallax = function () {
            let oVal = jQuery(window).scrollTop() / 5;
            let offset = parseInt(board.css('margin-top'), 0);
            let max = 96 + offset;
            if (oVal > max) {
                oVal = max;
            }
            bg.css({
                transform: 'translate3d(0,' + oVal + 'px,0)',
                '-webkit-transform': 'translate3d(0,' + oVal + 'px,0)',
                '-ms-transform': 'translate3d(0,' + oVal + 'px,0)',
                '-o-transform': 'translate3d(0,' + oVal + 'px,0)'
            });
            let toc = jQuery('#toc');
            if (toc) {
                jQuery('#toc-ctn').css({
                    'padding-top': oVal + 'px'
                });
            }
        };
        Fluid.utils.listenScroll(parallax);
    },

    registerScrollDownArrowEvent: function () {
        let scrollbar = jQuery('.scroll-down-bar');
        if (scrollbar.length === 0) {
            return;
        }
        scrollbar.on('click', function () {
            Fluid.utils.scrollToElement('#board', -jQuery('#navbar').height());
        });
    },

    registerScrollTopArrowEvent: function () {
        let topArrow = jQuery('#scroll-top-button');
        if (topArrow.length === 0) {
            return;
        }
        let board = jQuery('#board');
        if (board.length === 0) {
            return;
        }
        let posDisplay = false;
        let scrollDisplay = false;
        // Position
        let setTopArrowPos = function () {
            let boardRight = board[0].getClientRects()[0].right;
            let bodyWidth = document.body.offsetWidth;
            let right = bodyWidth - boardRight;
            posDisplay = right >= 50;
            topArrow.css({
                'bottom': posDisplay && scrollDisplay ? '20px' : '-60px',
                'right': right - 64 + 'px'
            });
        };
        setTopArrowPos();
        jQuery(window).resize(setTopArrowPos);
        // Display
        let headerHeight = board.offset().top;
        Fluid.utils.listenScroll(function () {
            let scrollHeight = document.body.scrollTop + document.documentElement.scrollTop;
            scrollDisplay = scrollHeight >= headerHeight;
            topArrow.css({
                'bottom': posDisplay && scrollDisplay ? '20px' : '-60px'
            });
        });
        // Click
        topArrow.on('click', function () {
            jQuery('body,html').animate({
                scrollTop: 0,
                easing: 'swing'
            });
        });
    },

    registerImageLoadedEvent: function () {
        if (!('NProgress' in window)) {
            return;
        }

        let bg = document.getElementById('banner');
        if (bg) {
            let src = bg.style.backgroundImage;
            let url = src.match(/\((.*?)\)/)[1].replace(/(['"])/g, '');
            let img = new Image();
            img.onload = function () {
                window.NProgress && window.NProgress.inc(0.2);
            };
            img.src = url;
            if (img.complete) {
                img.onload(undefined);
            }
        }

        let notLazyImages = jQuery('main img:not([lazyload])');
        let total = notLazyImages.length;
        for (const img of notLazyImages) {
            const old = img.onload;
            img.onload = function () {
                old && old();
                window.NProgress && window.NProgress.inc(0.5 / total);
            };
            if (img.complete) {
                img.onload();
            }
        }
    },

    billboard: function () {
        if (!('console' in window)) {
            return;
        }
        // eslint-disable-next-line no-console
        console.log(
            `
------------------------------------------------
|                                              |
|     ________  __            _        __      |
|    |_   __  |[  |          (_)      |  ]     |
|      | |_ \\_| | | __   _   __   .--.| |      |
|      |  _|    | |[  | | | [  |/ /'\`\\' |      |
|     _| |_     | | | \\_/ |, | || \\__/  |      |
|    |_____|   [___]'.__.'_/[___]'.__.;__]     |
|                                              |
|           Powered by Hexo x Fluid            |
|         GitHub: https://git.io/JqpVD         |
|                                              |
------------------------------------------------
    `
        );
    }
};