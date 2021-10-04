// Don't forget to add CSS for your custom styles.
CKEDITOR.addCss('figure[class*=easyimage-gradient]::before { content: ""; position: absolute; top: 0; bottom: 0; left: 0; right: 0; }' +
  'figure[class*=easyimage-gradient] figcaption { position: relative; z-index: 2; }' +
  '.easyimage-gradient-1::before { background-image: linear-gradient( 135deg, rgba( 115, 110, 254, 0 ) 0%, rgba( 66, 174, 234, .72 ) 100% ); }' +
  '.easyimage-gradient-2::before { background-image: linear-gradient( 135deg, rgba( 115, 110, 254, 0 ) 0%, rgba( 228, 66, 234, .72 ) 100% ); }');

CKEDITOR.replace('editor1', {
  extraPlugins: 'easyimage,autogrow',
  removePlugins: 'image,resize',
  removeDialogTabs: 'link:advanced',
  toolbar: [{
      name: 'document',
  items: ['Undo', 'Redo']
},
{
  name: 'styles',
  items: ['Format']
},
{
  name: 'basicstyles',
  items: ['Bold', 'Italic', 'Strike', '-', 'RemoveFormat']
},
{
  name: 'paragraph',
  items: ['NumberedList', 'BulletedList']
},
{
  name: 'links',
  items: ['Link', 'Unlink']
},
{
  name: 'insert',
  items: ['EasyImageUpload']
    }
  ],
  cloudServices_uploadUrl: 'https://33333.cke-cs.com/easyimage/upload/',
  // Note: this is a token endpoint to be used for CKEditor 4 samples only. Images uploaded using this token may be deleted automatically at any moment.
  // To create your own token URL please visit https://ckeditor.com/ckeditor-cloud-services/.
  cloudServices_tokenUrl: 'https://33333.cke-cs.com/token/dev/ijrDsqFix838Gh3wGO3F77FSW94BwcLXprJ4APSp3XQ26xsUHTi0jcb1hoBt',
  easyimage_styles: {
    gradient1: {
      group: 'easyimage-gradients',
  attributes: {
    'class': 'easyimage-gradient-1'
  },
  label: 'Blue Gradient',
  icon: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/gradient1.png',
  iconHiDpi: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/hidpi/gradient1.png'
},
gradient2: {
  group: 'easyimage-gradients',
  attributes: {
    'class': 'easyimage-gradient-2'
  },
  label: 'Pink Gradient',
  icon: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/gradient2.png',
  iconHiDpi: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/hidpi/gradient2.png'
},
noGradient: {
  group: 'easyimage-gradients',
  attributes: {
    'class': 'easyimage-no-gradient'
  },
  label: 'No Gradient',
  icon: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/nogradient.png',
  iconHiDpi: 'https://ckeditor.com/docs/ckeditor4/4.16.2/examples/assets/easyimage/icons/hidpi/nogradient.png'
    }
  },
  easyimage_toolbar: [
    'EasyImageFull',
'EasyImageSide',
'EasyImageGradient1',
'EasyImageGradient2',
'EasyImageNoGradient',
'EasyImageAlt'
  ],
	height: 600,
	autoGrow_maxHeight: 1000,
	autoGrow_bottomSpace: 50,
	width: '100%',
language: 'ko',
removeButtons: 'PasteFromWord'
} );
