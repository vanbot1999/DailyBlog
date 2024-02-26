// 文件路径: static/js/custom-tinymce.js

tinymce.init({
  selector: '#content',
  // 保留'code'插件，并可能需要添加更多插件以支持自定义对话框等功能
  plugins: 'code',
  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | code | customCodeButton',
  setup: function(editor) {
    editor.ui.registry.addButton('customCodeButton', {
      text: '插入代码',
      onAction: function() {
        // 打开一个自定义的对话框来选择语言和输入代码
        editor.windowManager.open({
          title: '插入代码',
          body: {
            type: 'panel',
            items: [
              { type: 'input', name: 'code', label: '代码' },
              { type: 'selectbox', name: 'language', label: '语言', items: [
                { text: 'SQL', value: 'sql' },
                { text: 'Python', value: 'python' }
                // 添加更多语言选项
              ]}
            ]
          },
          buttons: [
            { type: 'cancel', text: '取消' },
            { type: 'submit', text: '插入', primary: true }
          ],
          onSubmit: function(api) {
            var data = api.getData();
            // 插入选定语言的代码块
            editor.insertContent('<pre class="language-' + data.language + '"><code>' + tinymce.html.Entities.encodeAllRaw(data.code) + '</code></pre>');
            api.close();
          }
        });
      }
    });
    editor.on('change', function(e) {
          // 在这里调用 Prism.highlightAll() 可能不会立即生效，因为编辑器的内容更新可能是异步的
          // 因此，我们使用 setTimeout 来延迟高亮的应用
          setTimeout(function() { Prism.highlightAll(); }, 0);
        });
  },
});