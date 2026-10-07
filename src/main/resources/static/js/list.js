let boardList = [];
let pageInfo = {};
let pageNumberButtons;

// 데이터 로딩 후 출력
async function loadData(){
    searchKeyword()
    const params = new URLSearchParams(location.search);

    const page = params.get('page') ?? 0;
    const keyword = params.get('keyword') ?? '';


    await fetch(`/api/board?keyword=${keyword}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            boardList = data.pages.content;
            pageInfo = data.pages;
            console.log(pageInfo)
            console.log(data.pageGroup)

            boardListOutPut()
            pagination(pageInfo.number, data.pageGroup, page, keyword)
        })
}

// 게시글 검색
function searchKeyword() {
    document.getElementById("search-input").addEventListener('keyup',
        function(e){
        if(e.key === 'Enter'){
            const keyword = document.getElementById("search-input").value;
            if(keyword !== '' && keyword !== null) {
                location.href = `/board?keyword=${keyword}`;
            }
        }
    })
}

// 게시글 출력
function boardListOutPut() {
    const tbody = document.querySelector("tbody");
    let boardNumber = pageInfo.number*(pageInfo.size);
    tbody.innerHTML = ``;
    boardList.forEach(data => {
        const tr = document.createElement('tr');
        boardNumber += 1;
        tr.innerHTML =
            `<td class="boardNum">${boardNumber}</td>` +
            `<td class="boardTitle">
                <a href="/board/detail/${data.ID}">${data.TITLE}</a>
            </td>` +
            `<td class="boardWriter">${data.WRITER}</td>` +
            `<td class="created-day">${data.CREATED_AT.slice(0,10)}</td>` +
            `<td class="created-time">${data.CREATED_AT.slice(11,16)}</td>`;
        document.getElementById("boardListOutPut").append(tr);
    })
}

// 페이지네이션 (페이지 생성, 클릭 시 이동 이벤트)
function pagination(currentPage, pageGroup, page, keyword) {

    // 페이지 생성
    const setPageButtons = () => {
        const numberButtonWrapper = document.querySelector('.number-button-wrapper');
        numberButtonWrapper.innerHTML = '';

        const firstPage = (pageGroup - 1) * 10 + 1;
        const lastPage = Math.min(pageGroup * 10, pageInfo.totalPages);

        for (let i = firstPage; i <= lastPage; i++) {
            if(pageInfo.empty === true) return;
            numberButtonWrapper.innerHTML += `<span class="number-button">${i}</span>`;
        }
    }

    // 페이지 번호 클릭 이벤트
    const pageButtonsEvent = () => {
        pageNumberButtons = document.querySelectorAll('.number-button');

        pageNumberButtons.forEach((numberButton) => {
            numberButton.addEventListener('click', (e) => {
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${page - 1}`;
                } else{
                    location.href = `/board?page=${page - 1}`;
                }
            })
        })
    }

    // 이전, 다음 페이지 이동
    const pageMoveButton = () => {
        const prev = document.querySelector('.prev-page-button');
        const next = document.querySelector('.next-page-button');

        prev.addEventListener('click', (e) => {
            if(currentPage !== 0){
                if(keyword !== '' && keyword !== null){
                    location.href = `/board?keyword=${keyword}&page=${currentPage - 1}`;
                } else{
                    location.href = `/board?page=${currentPage - 1}`;
                }
            }
        })

        next.addEventListener('click', (e) => {
            if(currentPage !== pageInfo.totalPages - 1){
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${currentPage + 1}`;
                } else{
                    location.href = `/board?page=${currentPage + 1}`;
                }
            }
        })
    }

    // 이전 페이지 그룹 (<) , 다음 페이지 그룹 (>) 이동
    const pageGroupMoveButton = () => {
        const prevGroup= document.querySelector('.prev-pageGroup-button');
        const nextGroup = document.querySelector('.next-pageGroup-button');

        prevGroup.addEventListener('click', (e) => {
            if(pageGroup !== 1){
                if(keyword !== '' && keyword !== null){
                    location.href = `/board?keyword=${keyword}&page=${(pageGroup-2)*10}`;
                } else{
                    location.href = `/board?page=${(pageGroup-2)*10}`;
                }
            }
        })

        nextGroup.addEventListener('click', (e) => {
            if(pageGroup !== Math.ceil(pageInfo.totalPages/10)){
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${(pageGroup)*10}`;
                } else{
                    location.href = `/board?page=${(pageGroup)*10}`;
                }
            }
        })
    }

    setPageButtons();
    pageButtonsEvent();
    pageMoveButton();
    pageGroupMoveButton();

    pageNumberButtons.forEach((numberButton) => {
        if (numberButton.classList.contains('selected')){
            numberButton.classList.remove('selected');
        }
    })

    pageNumberButtons[currentPage%10].classList.add('selected');


}

loadData();