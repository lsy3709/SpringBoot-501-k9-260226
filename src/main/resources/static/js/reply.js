async function get1(bno) {
    // 화면에서 , axios 도구 이용해서, 스브프링 부트 서버에 비동기 통신 호출해서,
    // 게시글에 대한 , 댓글 목록 조회.
    const result = await axios.get(`/replies/list/${bno}`)
    // 데이터를 받아오는지 여부를 확인.
    console.log(result)
}