/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	var __weex_template__ = __webpack_require__(1)
	var __weex_style__ = __webpack_require__(2)
	var __weex_script__ = __webpack_require__(3)

	__weex_define__('@weex-component/fd824047a4d7b8cc1832cca3515660fb', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/fd824047a4d7b8cc1832cca3515660fb',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "classList": [
	    "modal-bg"
	  ],
	  "children": [
	    {
	      "type": "div",
	      "style": {
	        "flexDirection": "row",
	        "width": 750
	      },
	      "children": [
	        {
	          "type": "text",
	          "style": {
	            "backgroundColor": "#22ffff",
	            "flex": 1,
	            "textAlign": "center"
	          },
	          "events": {
	            "click": "clickAlert"
	          },
	          "attr": {
	            "value": "alert"
	          }
	        },
	        {
	          "type": "text",
	          "style": {
	            "backgroundColor": "#ffff22",
	            "flex": 1,
	            "textAlign": "center"
	          },
	          "events": {
	            "click": "clickToast"
	          },
	          "attr": {
	            "value": "toast"
	          }
	        }
	      ]
	    },
	    {
	      "type": "div",
	      "classList": [
	        "modal-bg"
	      ],
	      "style": {
	        "backgroundColor": "rgba(0,0,0,0.3)",
	        "marginTop": 50
	      },
	      "id": "alert",
	      "shown": function () {return this.showAlert},
	      "children": [
	        {
	          "type": "div",
	          "classList": [
	            "modal-alert"
	          ],
	          "children": [
	            {
	              "type": "text",
	              "style": {
	                "margin": 5
	              },
	              "attr": {
	                "value": function () {return this.message}
	              }
	            },
	            {
	              "type": "text",
	              "style": {
	                "textAlign": "center",
	                "margin": 5
	              },
	              "events": {
	                "click": "clickAlert_ok"
	              },
	              "attr": {
	                "value": function () {return this.oktitle}
	              }
	            }
	          ]
	        }
	      ]
	    },
	    {
	      "type": "div",
	      "classList": [
	        "modal-toast"
	      ],
	      "shown": function () {return this.showToast},
	      "children": [
	        {
	          "type": "text",
	          "style": {
	            "margin": 5
	          },
	          "attr": {
	            "value": function () {return this.message}
	          }
	        }
	      ]
	    }
	  ]
	}

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = {
	  "modal-bg": {
	    "position": "absolute",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "width": 750,
	    "top": 0,
	    "bottom": 0,
	    "left": 0,
	    "right": 0
	  },
	  "modal-alert": {
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "width": 500,
	    "backgroundColor": "#ffffff"
	  },
	  "modal-toast": {
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "width": 500,
	    "backgroundColor": "rgba(0,0,0,0.3)"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = function(module, exports, __weex_require__){'use strict';

	var animation = __weex_require__('@weex-module/animation');
	var modal = __weex_require__('@weex-module/modal');

	module.exports = {
		data: function () {return {
			oktitle: 'OK',
			message: 'message message message ',
			showAlert: false,
			showToast: false
		}},
		methods: {
			clickAlert_ok: function clickAlert_ok() {
				var self = this;
				self.showAlert = false;
				var params = {
					'message': self.message,
					'duration': 2
				};
			},
			clickAlert: function clickAlert() {
				var self = this;
				modal.alert({ 'message': self.message, 'oktitle': 'OK' });
			},
			clickToast: function clickToast() {
				var self = this;
				modal.toast({ 'message': self.message, 'doation': 2 });
			}
		},
		created: function created() {
			var self = this;
			for (var i = 0; i < 6; i++) {
				self.message += self.message;
			}
			var toast = self.$el('toast');
		}
	};}
	/* generated by weex-loader */


/***/ }
/******/ ]);