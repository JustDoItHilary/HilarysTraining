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

	__weex_define__('@weex-component/11444e7023dd783477ebc9af6d71f3d8', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/11444e7023dd783477ebc9af6d71f3d8',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "classList": [
	    "wrapper"
	  ],
	  "children": [
	    {
	      "type": "div",
	      "classList": [
	        "box"
	      ],
	      "id": "box"
	    },
	    {
	      "type": "text",
	      "attr": {
	        "value": function () {return 'Red box: ' + (this.boxposition)}
	      }
	    },
	    {
	      "type": "text",
	      "attr": {
	        "value": function () {return 'Viewport: ' + (this.viewportposition)}
	      }
	    },
	    {
	      "type": "div",
	      "classList": [
	        "row"
	      ],
	      "children": [
	        {
	          "type": "div",
	          "events": {
	            "click": "getBoxPosition"
	          },
	          "classList": [
	            "button"
	          ],
	          "children": [
	            {
	              "type": "text",
	              "attr": {
	                "value": "Get red Box position"
	              }
	            }
	          ]
	        },
	        {
	          "type": "div",
	          "events": {
	            "click": "getViewportPosition"
	          },
	          "classList": [
	            "button"
	          ],
	          "children": [
	            {
	              "type": "text",
	              "attr": {
	                "value": "Get Viewport position"
	              }
	            }
	          ]
	        }
	      ]
	    }
	  ]
	}

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = {
	  "wrapper": {
	    "position": "absolute",
	    "top": 0,
	    "right": 0,
	    "bottom": 0,
	    "left": 0
	  },
	  "box": {
	    "width": 300,
	    "height": 300,
	    "backgroundColor": "#ff0000",
	    "position": "absolute",
	    "top": 300,
	    "left": 200
	  },
	  "row": {
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "flexDirection": "row"
	  },
	  "button": {
	    "flex": 1,
	    "backgroundColor": "#dddddd",
	    "borderLeftWidth": 1,
	    "borderLeftColor": "#333333",
	    "borderLeftStyle": "solid"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = function(module, exports, __weex_require__){'use strict';

	var _stringify = __webpack_require__(4);

	var _stringify2 = _interopRequireDefault(_stringify);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var dom = __weex_require__('@weex-module/dom');

	module.exports = {
	  data: function () {return {
	    boxposition: '',
	    viewportposition: ''
	  }},
	  methods: {
	    getBoxPosition: function getBoxPosition() {
	      var el = this.$el('box');
	      var self = this;

	      dom.getComponentRect(el, function (result) {
	        self.boxposition = (0, _stringify2.default)(result);
	      });
	    },
	    getViewportPosition: function getViewportPosition() {
	      var self = this;

	      dom.getComponentRect('viewport', function (result) {
	        self.viewportposition = (0, _stringify2.default)(result);
	      });
	    }
	  }
	};}
	/* generated by weex-loader */


/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = { "default": __webpack_require__(5), __esModule: true };

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	var core  = __webpack_require__(6)
	  , $JSON = core.JSON || (core.JSON = {stringify: JSON.stringify});
	module.exports = function stringify(it){ // eslint-disable-line no-unused-vars
	  return $JSON.stringify.apply($JSON, arguments);
	};

/***/ },
/* 6 */
/***/ function(module, exports) {

	var core = module.exports = {version: '2.4.0'};
	if(typeof __e == 'number')__e = core; // eslint-disable-line no-undef

/***/ }
/******/ ]);