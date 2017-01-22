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

	__weex_define__('@weex-component/ff039b5cab1dcec20eace05fd499749e', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/ff039b5cab1dcec20eace05fd499749e',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "children": [
	    {
	      "type": "list",
	      "classList": [
	        "list"
	      ],
	      "children": [
	        {
	          "type": "header",
	          "classList": [
	            "header"
	          ],
	          "children": [
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": "Search Results"
	              }
	            }
	          ]
	        },
	        {
	          "type": "refresh",
	          "style": {
	            "width": 750,
	            "padding": 30
	          },
	          "events": {
	            "refresh": "refreshData"
	          },
	          "attr": {
	            "display": function () {return this.refreshDisplay}
	          },
	          "children": [
	            {
	              "type": "text",
	              "classList": [
	                "text"
	              ],
	              "attr": {
	                "value": "↓ Pull to refresh"
	              }
	            },
	            {
	              "type": "loading-indicator",
	              "classList": [
	                "indicator"
	              ]
	            }
	          ]
	        },
	        {
	          "type": "cell",
	          "append": "tree",
	          "classList": [
	            "row"
	          ],
	          "repeat": {
	            "expression": function () {return this.items},
	            "value": "item"
	          },
	          "id": function () {return 'item-' + (this.$index)},
	          "children": [
	            {
	              "type": "div",
	              "classList": [
	                "lines"
	              ],
	              "children": [
	                {
	                  "type": "text",
	                  "classList": [
	                    "item"
	                  ],
	                  "attr": {
	                    "value": "Repo name:"
	                  }
	                },
	                {
	                  "type": "a",
	                  "attr": {
	                    "href": function () {return this.item.repo_url}
	                  },
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "link"
	                      ],
	                      "attr": {
	                        "value": function () {return this.item.full_name}
	                      }
	                    }
	                  ]
	                }
	              ]
	            },
	            {
	              "type": "div",
	              "children": [
	                {
	                  "type": "text",
	                  "classList": [
	                    "item"
	                  ],
	                  "attr": {
	                    "value": function () {return 'Repo star: ' + (this.item.stargazers_count)}
	                  }
	                }
	              ]
	            }
	          ]
	        },
	        {
	          "type": "loading",
	          "events": {
	            "loading": "loadingData"
	          },
	          "style": {
	            "width": 750,
	            "padding": 30
	          },
	          "attr": {
	            "display": function () {return this.loadingDisplay}
	          },
	          "children": [
	            {
	              "type": "text",
	              "classList": [
	                "text"
	              ],
	              "attr": {
	                "value": function () {return this.loadingText}
	              }
	            }
	          ]
	        }
	      ]
	    },
	    {
	      "type": "div",
	      "classList": [
	        "up"
	      ],
	      "events": {
	        "click": "goToTop"
	      },
	      "children": [
	        {
	          "type": "image",
	          "classList": [
	            "img"
	          ],
	          "attr": {
	            "src": "https://img.alicdn.com/tps/TB1ZVOEOpXXXXcQaXXXXXXXXXXX-200-200.png"
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
	  "header": {
	    "padding": 25,
	    "backgroundColor": "#efefef",
	    "borderBottomColor": "#eeeeee",
	    "borderBottomWidth": 2,
	    "borderBottomStyle": "solid"
	  },
	  "title": {
	    "textAlign": "center"
	  },
	  "text": {
	    "textAlign": "center"
	  },
	  "list": {
	    "backgroundColor": "#ffffff"
	  },
	  "row": {
	    "padding": 20,
	    "borderBottomColor": "#eeeeee",
	    "borderBottomWidth": 2,
	    "borderBottomStyle": "solid"
	  },
	  "up": {
	    "width": 70,
	    "height": 70,
	    "position": "fixed",
	    "right": 20,
	    "bottom": 20
	  },
	  "img": {
	    "width": 70,
	    "height": 70
	  },
	  "lines": {
	    "flexDirection": "row"
	  },
	  "link": {
	    "color": "#008cff",
	    "textDecoration": "underline"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = function(module, exports, __weex_require__){'use strict';

	var dom = __weex_require__('@weex-module/dom') || {};
	var stream = __weex_require__('@weex-module/stream') || {};
	var modal = __weex_require__('@weex-module/modal') || {};

	var SEARCH_URL = 'https://api.github.com/search/repositories?q=language:javascript&sort=stars&order=desc',
	    PAGE_URL = 'http://dotwe.org/raw/dist/f1fa0895d0fa0fd80896e02a589443dd.js';

	module.exports = {
	  data: function () {return {
	    isLoaded: true,
	    page: 1,
	    loadingDisplay: 'hide',
	    refreshDisplay: 'hide',
	    loadingText: 'Loading...',
	    items: []
	  }},
	  created: function created() {
	    var url = SEARCH_URL + '&page=' + this.page;

	    this.renderData(url);

	    this.page++;
	  },
	  methods: {
	    renderData: function renderData(url) {
	      var self = this;
	      var paperUrl = 'http://192.168.100.140:8080/test_yn/exam/Exam/examList';

	      stream.fetch({
	        method: 'GET',
	        url: url,
	        type: 'json'
	      }, function (res) {
	        console.log('i am the callback ' + res.data.items.length);
	        try {
	          var results = res.data.items || [];

	          if (Array.isArray(results)) {
	            for (var i = 0; i < results.length; i++) {
	              var repo_url = results[i].html_url;
	              if (repo_url) {
	                results[i].repo_url = self.processUrl(repo_url);
	              }
	              self.items.push(results[i]);
	            }
	          }
	        } catch (e) {}
	      }, function (res) {});
	    },
	    loadingData: function loadingData(e) {
	      var url = SEARCH_URL + '&page=' + this.page;
	      var self = this;

	      if (self.isLoaded === false) return;

	      self.loadingDisplay = 'show';

	      if (self.page <= 10) {
	        self.renderData(url);
	        self.page++;
	      } else {
	        self.loadingDisplay = 'hide';
	        self.loadingText = 'NO MORE!';
	      }
	    },
	    goToTop: function goToTop(e) {
	      dom.scrollToElement(this.$el('item-0'), {
	        offset: -100
	      });
	    },
	    refreshData: function refreshData(e) {
	      var url = SEARCH_URL + '&page=1';

	      if (this.isLoaded === false) return;

	      this.refreshDisplay = 'show';

	      modal.toast({
	        'message': 'Refreshing...',
	        'duration': 1
	      });

	      this.items = [];
	      this.page = 1;
	      this.renderData(url);

	      this.refreshDisplay = 'hide';
	    },
	    processUrl: function processUrl(url) {
	      var platform = this.$getConfig().env.platform.toLowerCase();

	      if (url) {
	        if (platform === 'ios') {
	          return PAGE_URL + '?weburl=' + url;
	        } else if (platform === 'web') {
	          return url;
	        } else {
	          var encodeUrl = encodeURIComponent(url);
	          return PAGE_URL + '?weburl=' + encodeUrl;
	        }
	      }
	    }
	  }
	};}
	/* generated by weex-loader */


/***/ }
/******/ ]);