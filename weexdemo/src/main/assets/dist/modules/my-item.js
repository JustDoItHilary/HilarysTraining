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

	__weex_define__('@weex-component/e500705729e28e167f76e392385859c8', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/e500705729e28e167f76e392385859c8',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "children": [
	    {
	      "type": "div",
	      "style": {
	        "flexDirection": "row"
	      },
	      "children": [
	        {
	          "type": "input",
	          "id": "input",
	          "style": {
	            "margin": 20,
	            "flex": 1
	          },
	          "attr": {
	            "placeholder": "请输入版本号。。",
	            "autofocus": "true"
	          },
	          "events": {
	            "input": "onInput_num"
	          }
	        },
	        {
	          "type": "text",
	          "style": {
	            "margin": 20,
	            "backgroundColor": "#5592e1",
	            "textAlign": "center"
	          },
	          "events": {
	            "click": "search"
	          },
	          "attr": {
	            "value": "search"
	          }
	        }
	      ]
	    },
	    {
	      "type": "scroller",
	      "children": [
	        {
	          "type": "div",
	          "classList": [
	            "all"
	          ],
	          "children": [
	            {
	              "type": "text",
	              "classList": [
	                "time"
	              ],
	              "attr": {
	                "value": function () {return this.time}
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": function () {return this.title}
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "author"
	              ],
	              "attr": {
	                "value": function () {return '作者/ ' + (this.author)}
	              }
	            },
	            {
	              "type": "div",
	              "classList": [
	                "div-tab"
	              ],
	              "children": [
	                {
	                  "type": "div",
	                  "repeat": {
	                    "expression": function () {return this.tab_items},
	                    "value": "tab"
	                  },
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text-tab"
	                      ],
	                      "style": {
	                        "backgroundColor": function () {return this.tab.background_color}
	                      },
	                      "attr": {
	                        "value": function () {return this.tab.tab}
	                      }
	                    }
	                  ]
	                }
	              ]
	            },
	            {
	              "type": "text",
	              "classList": [
	                "excerpt"
	              ],
	              "attr": {
	                "value": function () {return this.content}
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
	  "all": {
	    "padding": 20
	  },
	  "time": {
	    "color": "#888888",
	    "marginTop": 20
	  },
	  "title": {
	    "fontSize": 35,
	    "fontWeight": "bold",
	    "marginTop": 15
	  },
	  "author": {
	    "paddingLeft": 20,
	    "fontSize": 30,
	    "marginTop": 10
	  },
	  "excerpt": {
	    "color": "#000000",
	    "fontSize": 30
	  },
	  "text-tab": {
	    "padding": 6,
	    "marginRight": 20,
	    "color": "#ff5555",
	    "fontSize": 30,
	    "textAlign": "center"
	  },
	  "div-tab": {
	    "padding": 20,
	    "flexDirection": "row",
	    "justifyContent": "flex-start",
	    "alignItems": "flex-start"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = function(module, exports, __weex_require__){'use strict';

	var modal = __weex_require__('@weex-module/modal');
	var stream = __weex_require__('@weex-module/stream');

	module.exports = {
	    data: function () {return {
	        url: 'http://v3.wufazhuce.com:8000/api/serialcontent/',
	        time: '2016-12-20',
	        title: '灰猫',
	        author: '康夫',
	        excerpt: '真是意外，和灰猫住了这么久，现在才知道这家伙还有大名。',
	        content: '',
	        tab_items: [{ 'type': 1, 'tab': '有通知', 'background_color': '#66ddff' }, { 'type': 2, 'tab': '有消息', 'background_color': '#ffdd66' }, { 'type': 3, 'tab': '放假了', 'background_color': '#ffaaff' }, { 'type': 4, 'tab': '嗨皮了', 'background_color': '#99cc00' }],
	        input_num: '217'
	    }},
	    created: function created() {
	        var self = this;

	        self.getData(self.url + self.input_num, function () {});
	    },
	    methods: {
	        getUrlParams: function getUrlParams(url) {
	            var queryStr = url.split('?')[1] || '';
	            var queryArr = queryStr.split('&');
	            var ret = {};

	            queryArr.forEach(function (item, index) {
	                var kv = item.split('=');
	                var key = kv[0];
	                var value = kv[1];

	                if (key) {
	                    ret[key] = value;
	                }
	            });

	            return ret;
	        },
	        getUrl: function getUrl() {
	            var bundleUrl = this.$getConfig().bundleUrl || '';
	            var bundleUrlParams = this.getUrlParams(bundleUrl);

	            var webUrl = bundleUrlParams.weburl || '';
	            this.url = decodeURIComponent(webUrl);
	        },
	        getData: function getData(url, callback) {
	            var self = this;
	            console.log('i am the callback ' + url);
	            stream.fetch({ method: 'GET', url: url, type: 'json' }, function (res) {
	                try {
	                    var results = res.data.data || [];
	                    self.title = results.title;
	                    self.content = results.content;
	                    self.author = results.author.user_name;
	                } catch (e) {}
	            }, function (res) {});
	        },
	        onInput_num: function onInput_num(el) {
	            var self = this;
	            if (!el.value) return;
	            self.input_num = el.value;
	            console.log('i am the callback ' + el.value);
	        },
	        search: function search() {
	            var self = this;
	            self.getData(self.url + self.input_num, function () {});
	        }
	    }
	};}
	/* generated by weex-loader */


/***/ }
/******/ ]);