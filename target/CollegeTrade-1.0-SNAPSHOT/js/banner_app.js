var banner_vue = new Vue({
    el:"#app-banner",
    data:{
        notices:[]
    },
    methods:{
        showNotice(){
            axios.post("NoticeServlet?action=findAll").then(response=>{
                if(response.data.status){
                    this.notices = response.data.data;
                }else{
                    alert(response.data.message);
                }
            })
        }
    },
    created(){
        this.showNotice();
    }
})