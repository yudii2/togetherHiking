(()=>{
  document.querySelectorAll('.gnb_menu').forEach(gnb=>{
    
    gnb.addEventListener('click',e=>{
      /* 초기화 */
      document.querySelectorAll('.gnb li').forEach(li=>{
        li.children[0].style.color='';
      });
      
      if(e.target.dataset.gnb == gnb.dataset.gnb){
        gnb.style.color='var(--point-color)';
        
      };
    })
  })
})();