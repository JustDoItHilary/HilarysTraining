define('@weex-component/tc_support_button', function (require, exports, module) {

;
  module.exports = {
    data: function () {return {
      title:'Button Click'
    }},
  }


;module.exports.style = {
  "btn": {
    "width": 400,
    "height": 50,
    "backgroundColor": "#EEEEEE",
    "justifyContent": "center"
  },
  "title": {
    "fontSize": 20,
    "textAlign": "center"
  },
  "border": {
    "borderWidth": 1,
    "borderColor": "#696969",
    "borderStyle": "solid",
    "borderRadius": 5
  },
  "font": {
    "fontFamily": "Times New Roman"
  }
}

;module.exports.template = {
  "type": "div",
  "classList": [
    "btn",
    "border"
  ],
  "attr": {
    "flagid": function () {return this.flagid}
  },
  "events": {
    "click": "btnTouch"
  },
  "children": [
    {
      "type": "text",
      "classList": [
        "title",
        "font"
      ],
      "attr": {
        "value": function () {return this.title}
      }
    }
  ]
}

;})

// module

define('@weex-component/tc_support_maintitle', function (require, exports, module) {

;
  module.exports = {
    data: function () {return {
      title:''
    }},
    methods: {
    }
  }


;module.exports.style = {
  "title-div": {
    "backgroundColor": "#66CDAA",
    "height": 66,
    "paddingBottom": 10
  },
  "title": {
    "fontWeight": "bold",
    "fontSize": 36,
    "marginTop": 15,
    "marginLeft": 5,
    "textAlign": "center"
  },
  "font": {
    "fontFamily": "Times New Roman"
  },
  "div-split": {
    "height": 2,
    "borderWidth": 1,
    "borderStyle": "dashed",
    "borderColor": "#375830"
  }
}

;module.exports.template = {
  "type": "div",
  "classList": [
    "title-div"
  ],
  "children": [
    {
      "type": "text",
      "classList": [
        "title",
        "font"
      ],
      "attr": {
        "value": function () {return this.title}
      }
    }
  ]
}

;})

// module

define('@weex-component/tc_support_scroller', function (require, exports, module) {

;

;module.exports.style = {
  "root": {
    "flexDirection": "column",
    "backgroundColor": "#ffffff"
  },
  "screen": {
    "flexDirection": "column",
    "backgroundColor": "#ffffff",
    "width": 750,
    "height": 1000,
    "borderWidth": 3,
    "borderColor": "#00ff00"
  }
}

;module.exports.template = {
  "type": "scroller",
  "classList": [
    "screen"
  ],
  "children": [
    {
      "type": "content"
    }
  ]
}

;})

// module

define('@weex-component/TC_BizModule_AppMonitor_ErrorData', function (require, exports, module) {

;
  module.exports = {
    data: function () {return {
      itemList:[
        {boder:'border-green', title:'~\(≧▽≦)/~啦啦啦，有美景', imgUrl:'https://gd2.alicdn.com/imgextra/i2/TB1ThbXIVXXXXbNXFXXXXXXXXXX_!!0-item_pic.jpg_400x400.jpg'},
        {boder:'border-pink', title:'~\(≧▽≦)/~啦啦啦，有美食', imgUrl:'https://img.alicdn.com/bao/uploaded/i3/TB1lauIKXXXXXXQXXXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg'}
        ]
    }},
    methods: {
      toggle: function () {
        this.itemList = "~~~~~~~~~~~~~~~~~~~~~";
      }
    }
  }


;module.exports.style = {
  "component": {
    "margin": 10,
    "flexDirection": "row",
    "alignItems": "center"
  },
  "border-green": {
    "borderWidth": 5,
    "borderStyle": "solid",
    "borderColor": "#00FF00"
  },
  "border-pink": {
    "borderWidth": 5,
    "borderStyle": "solid",
    "borderColor": "#FF00FF"
  },
  "text": {
    "height": 30,
    "borderWidth": 1,
    "borderStyle": "solid",
    "margin": 2,
    "paddingLeft": 5,
    "fontSize": 20
  },
  "img": {
    "margin": 2,
    "width": 400,
    "height": 400
  }
}

;module.exports.template = {
  "type": "tc_support_scroller",
  "children": [
    {
      "type": "tc_support_maintitle",
      "attr": {
        "title": "TC_BizModule_AppMonitor_ErrorData"
      }
    },
    {
      "type": "div",
      "style": {
        "margin": 10
      },
      "children": [
        {
          "type": "tc_support_button",
          "style": {
            "margin": 5
          },
          "attr": {
            "flagid": "btn-if",
            "title": "赋值错误数据",
            "btnTouch": function () {return this.toggle}
          }
        },
        {
          "type": "div",
          "classList": function () {return ['component', this.boder]},
          "repeat": function () {return this.itemList},
          "children": [
            {
              "type": "image",
              "classList": [
                "img"
              ],
              "attr": {
                "src": function () {return this.imgUrl}
              }
            },
            {
              "type": "text",
              "classList": [
                "text"
              ],
              "attr": {
                "value": function () {return this.title}
              }
            }
          ]
        }
      ]
    }
  ]
}

;})

// require module
bootstrap('@weex-component/TC_BizModule_AppMonitor_ErrorData', {"transformerVersion":"0.3.1"})