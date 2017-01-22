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

	__weex_define__('@weex-component/3d6fdfaa9f809e73b9b0a07ed49554cc', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/3d6fdfaa9f809e73b9b0a07ed49554cc',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "style": {
	    "padding": 10
	  },
	  "children": [
	    {
	      "type": "text",
	      "style": {
	        "padding": 10
	      },
	      "attr": {
	        "value": function () {return 'pick value: ' + (this.pickvalue)}
	      }
	    },
	    {
	      "type": "text",
	      "classList": [
	        "picker"
	      ],
	      "events": {
	        "click": "pick"
	      },
	      "attr": {
	        "value": "pick data"
	      }
	    }
	  ]
	}

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = {
	  "picker": {
	    "padding": 10,
	    "borderWidth": 2,
	    "borderRadius": 5,
	    "borderColor": "#22ffff",
	    "textAlign": "center"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = function(module, exports, __weex_require__){'use strict';

	var picker = __weex_require__('@weex-module/picker');
	var modal = __weex_require__('@weex-module/modal');

	module.exports = {
		data: function () {return {
			pickvalue: ''
		}},
		methods: {
			pick: function pick() {
				var self = this;
				picker.pickTime({ value: '08:30' }, function (e) {
					if (e.result == 'success') {
						mdoal.toast({ 'message': 'success', 'doation': 1 });
						self.value = e.data;
					}
				});
			}
		}
	};}
	/* generated by weex-loader */


/***/ }
/******/ ]);