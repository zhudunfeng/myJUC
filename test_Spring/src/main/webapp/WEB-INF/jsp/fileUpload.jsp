<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title></title>
  <!--引入CSS-->
  <link rel="stylesheet" type="text/css" href="/assets/js/webuploader-0.1.5/webuploader.css">
  <!--引入JS-->
  <script src="/assets/js/jquery-3.6.0.min.js"></script>
  <script src="/assets/js/webuploader-0.1.5/webuploader.min.js"></script>
  <script type="text/javascript">
    $(function () {
      //开始上传按钮
      var $btn = $('#ctlBtn');
      //文件信息显示区域
      var $list = $('#thelist');
      //当前状态
      var state = 'pending';
      //初始化Web Uploader
      var uploader = WebUploader.create({
        // swf文件路径
        swf: '${pageContext}/assets/js/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        //server: 'http://www.hangge.com/upload.php',
        server: '/fileupload?path=uploadfilemanager',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        //设置文佳上传的类型格式
        // accept: {  //不建议使用，使用时选择文件div失效
        //   title: 'file',
        //  extensions: 'xls,xlsx,word,doc,ppt,docx,rtf,ppt,txt,pptx,pdf',
        //  mimeTypes: '.xls,.xlsx,.word,.doc,.ppt,.docx,.rtf,.ppt,.txt,.pptx,.pdf'
        // }
      });

      // 当有文件被添加进队列的时候（选择文件后调用）
      uploader.on('fileQueued', function (file) {
        $list.append('<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
                '</div>');
      });

      // 文件上传过程中创建进度条实时显示。
      uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id);
        $li.find('p.state').text('上传中（' + parseInt(percentage * 100) + '%）');
      });

      // 文件上传成功后会调用
      uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('p.state').text('已上传');
        savefilemanager("/vod/uploadfilemanager/" + file.name, file.name);
      });

      // 文件上传失败后会调用
      uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
      });

      // 文件上传完毕后会调用（不管成功还是失败）
      uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
      });

      // all事件（所有的事件触发都会响应到）
      uploader.on('all', function (type) {
        if (type === 'startUpload') {
          state = 'uploading';
        } else if (type === 'stopUpload') {
          state = 'paused';
        } else if (type === 'uploadFinished') {
          state = 'done';
        }

        if (state === 'uploading') {
          $btn.text('暂停上传');
        } else {
          $btn.text('开始上传');
        }
      });

      // 开始上传按钮点击事件响应
      $btn.on('click', function () {
        if (state === 'uploading') {
          uploader.stop();
        } else {
          uploader.upload();
        }
      });
    });
  </script>
  <style>
    #picker {
      display: inline-block;
    }

    #ctlBtn {
      position: relative;
      display: inline-block;
      cursor: pointer;
      background: #EFEFEF;
      padding: 10px 15px;
      color: #2E2E2E;
      text-align: center;
      border-radius: 3px;
      overflow: hidden;
    }

    #ctlBtn:hover {
      background: #DDDDDD;
    }
  </style>
  <style>
    #picker {
      display: inline-block;
    }

    #ctlBtn {
      position: relative;
      display: inline-block;
      cursor: pointer;
      background: #EFEFEF;
      padding: 10px 15px;
      color: #2E2E2E;
      text-align: center;
      border-radius: 3px;
      overflow: hidden;
    }

    #ctlBtn:hover {
      background: #DDDDDD;
    }
  </style>
</head>

<body>
<div id="uploader" class="wu-example">
  <div class="btns">
    <div id="picker">选择文件</div>
    <div id="ctlBtn" class="webuploader-upload">开始上传</div>
  </div>
  <!--用来存放文件信息-->
  <div id="thelist" class="uploader-list"></div>
</div>
</body>

</html>
